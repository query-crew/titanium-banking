""" Branch data producer that creates dummy branch data """
import sys
import os
import json
from dotenv import load_dotenv
from faker import Faker
import requests


class BranchProducer:
    """
    BranchProducer that will create dummy data for the branch microservice
    takes various parameters
    -d to delete all records
    -l to list all branches
    -n to denote the number of records to insert
    """

    def __init__(self, n_records=0):
        self.n_records = int(n_records)
        self.records_produced = []

        load_dotenv()
        self.base_url = os.getenv("BASE_URL")
        self.insert_url = os.getenv("INSERT_URL")
        self.get_url = os.getenv("GET_URL")
        self.delete_url = os.getenv("DELETE_URL")

    def __repr__(self):
        return f"Branch Producer to generate {self.n_records} records"

    def verify_records(self, total_start):
        """ verify that the total items in the record match the amount to insert or delete """
        total_end = self.get_all_branches()["totalItems"]
        if total_start + self.n_records == total_end:
            return True
        return False

    def branch_generator(self):
        """ with faker library will generate n records """
        for _ in range(self.n_records):
            fake = Faker()
            branch_name = fake.last_name() + fake.last_name()
            address = fake.address().split("\n")
            address_line = address[0]
            address_line_2 = "N/A"
            if fake.boolean(chance_of_getting_true=25):
                address_line_2 = "Suite " + fake.building_number()

            city = address[1].split(",")[0]
            address_part_2 = address[1].split()
            state = address_part_2[-2]
            zipcode = address_part_2[-1]
            branch_details = fake.paragraph(nb_sentences=2)

            branch_dict = {
                "branchName": branch_name,
                "addressLine1": address_line,
                "addressLine2": address_line_2,
                "city": city,
                "state": state,
                "zipCode": zipcode,
                "branchDetails": branch_details,
            }

            yield branch_dict

    def records_insert(self, generator):
        """ insert records from the generator
        generator is to provide ability for lots of requests
        """
        url = self.base_url + self.insert_url

        try:
            while True:
                data = next(generator)
                if data is not None:
                    resp = requests.request("POST", url, json=data)
                    print(f"INSERTED {data['branchName']}")
                    continue
                break

        except requests.exceptions.HTTPError as err:
            print(err, resp.status_code)

    def get_all_branches(self):
        """ return all branchs in json format """
        url = self.base_url + self.get_url
        resp = requests.request("GET", url)
        return json.loads(resp.content.decode("utf-8"))

    def delete_all_branches(self):
        """ delete all branches available """
        url = self.base_url + self.delete_url
        resp = requests.request("DELETE", url)
        return resp.status_code


def main():
    """ main driver parses arguments and handles the StopIteration exception
    """

    if len(sys.argv) < 2:
        raise ValueError(
            "Please enter input formatted as \n \
            python branch_producer.py [n records to produce] [args] \
            "
        )
    try:
        if "-n" in sys.argv:
            num_index = sys.argv.index("-n") + 1
            producer = BranchProducer(sys.argv[num_index])
        else:
            producer = BranchProducer()

        if "-d" in sys.argv:
            producer.delete_all_branches()
            total_start = 0

        if producer.n_records != 0:
            total_start = producer.get_all_branches()["totalItems"]
            generator = producer.branch_generator()
            producer.records_insert(generator)

    except Exception as err:
        print(err)

    finally:
        if "-l" in sys.argv and producer.verify_records(total_start):
            obj = producer.get_all_branches()
            json_object = json.dumps(obj, indent=4)
            print(json_object)


if __name__ == "__main__":
    main()
