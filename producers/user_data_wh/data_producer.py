import sys
import os
from dotenv import load_dotenv
from faker import Faker
import requests


""" Data Producer Module for User, Creates dummy data
for Users and Members
and sends it to endpoint denoted in .env file """


class DataProducer:
    """
    Takes positional parameters data_type, n_records
    data_types are: 'member' or 'user'
    n_records are: integer values denoting how many records to produce

    Class that will generate all required data given a type
    and n number of records to produce, producer.produce will return a
    generator object of records
    """

    def __init__(self, data_type, n_records):
        self.data_type = data_type
        self.n_records = n_records

        load_dotenv()
        self.login_route = os.getenv("LOGIN_ROUTE")
        self.base_url = os.getenv("BASE_URL")
        self.crt_path = os.getenv("CRT_PATH")

    def __repr__(self):
        return f"Data Producer for {self.data_type} with {self.n_records} \
        number of records"

    def verify_record(self):
        """ attempt to login with inserted credentials """
        url = self.base_url + self.login_route
        data = {"username": "iamauser", "password": "mypass"}
        r = requests.request("POST", url, json=data, verify=self.crt_path)
        print(r.status_code)
        print(r)

    def insert_records(self):
        """ using produced records generator insert all
        users into user defined route """

    def produce_records(self):
        """ create n_record generator with number of records
            using the faker library  """


def main():
    """ Main driver will read user args and instantiate class
    and class calls
    """

    if len(sys.argv) < 3:
        raise ValueError(
            "please enter input formatted as: \n"
            + "python data_producer.py [user|member]"
            + " [number of records]"
        )

    try:
        producer = DataProducer(sys.argv[1], int(sys.argv[2]))
        producer.verify_record()

    except Exception as err:
        print(err)


if __name__ == "__main__":
    main()
