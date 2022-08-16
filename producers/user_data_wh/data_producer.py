""" Data Producer Module for User, Creates dummy data
for Users and Members
and sends it to endpoint denoted in .env file """
import sys
import os
import re
import random
from dotenv import load_dotenv
from faker import Faker
import requests


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
        self.create_user_route = os.getenv("USER_CREATE_ROUTE")
        self.create_member_route = os.getenv("MEMBER_CREATE_ROUTE")
        self.base_url = os.getenv("BASE_URL")
        self.crt_path = os.getenv("CRT_PATH")

        self.records_produced = []

    def __repr__(self):
        return f"Data Producer for {self.data_type} with {self.n_records} \
        number of records"

    def verify_records(self):
        """ attempt to login with inserted credentials """
        url = self.base_url + self.login_route
        for produced_data in self.records_produced:
            data = {}
            data["username"] = produced_data["username"]
            data["password"] = produced_data["password"]
            resp = requests.request("POST", url, json=data, verify=self.crt_path)
            print(resp.content)
            if resp.status_code != 200:
                raise requests.exceptions.HTTPError(
                    f"cannot verify user credentials: {data['username']}"
                )
            return True

    def user_generator(self, test=False):
        """ create admin user n_record generator with number of records
            using the faker library  """
        for _ in range(self.n_records):
            fake = Faker()
            user_type = "admin"
            username = "".join(fake.name().split()).lower()
            email = username + "@gmail.com"
            password = fake.password()
            user_dict = {
                "userType": user_type,
                "username": username,
                "email": email,
                "password": password,
            }
            if test:
                self.records_produced.append(user_dict)
            yield user_dict

    def member_generator(self, test=False):
        """ Create regular member n_record generator using faker library """
        for _ in range(self.n_records):
            fake = Faker()
            name = fake.name().split()
            email = "".join(name).lower() + "@gmail.com"
            username = "".join(name).lower()
            password = fake.password(length=13, special_chars=False)
            firstname = name[0].strip().lower()
            lastname = name[1].strip().lower()
            dob = fake.date_of_birth(minimum_age=18).strftime("%Y-%m-%d")
            ssn = fake.ssn()
            full_address = fake.address().split()
            address = " ".join(full_address[: -3 or None])
            city = fake.city().replace(" ", "")
            state = full_address[-2]
            zipcode = full_address[-1]

            member_dict = {
                "email": email,
                "username": username,
                "password": password,
                "firstName": re.sub(r"\W+", "", firstname),
                "lastName": re.sub(r"\W+", "", lastname),
                "phone": phn(),
                "dateOfBirth": dob,
                "socialSecurityNumber": ssn,
                "addressLine1": re.sub(r"\W+", "", address),
                "city": city.strip(),
                "state": state,
                "zipcode": zipcode,
            }

            if test:
                self.records_produced.append(member_dict)
            yield member_dict

    def records_insert(self, generator):
        """ depending on data type insert data produced from generator to route """
        if self.data_type == "user":
            route = self.create_user_route
        else:
            route = self.create_member_route

        headers = {"Content-Type": "application/json"}

        url = self.base_url + route

        try:
            while True:
                data = next(generator)
                if data is not None:
                    resp = requests.request(
                        "POST", url, headers=headers, json=data, verify=self.crt_path
                    )
                    if resp.status_code != 201:
                        raise requests.exceptions.HTTPError()
                    print(f"INSERTED {data['username']} as {self.data_type}")
                    continue
                break
        except requests.exceptions.HTTPError as err:
            print(err, resp.status_code)
            if resp.status_code == 400:
                return True
            return False
        return True


def phn():
    """ Nice XXX-XXX-XXXX phone number gen I lifted off of stack overflow """
    num = "0000000000"
    while "9" in num[3:6] or num[3:6] == "000" or num[6] == num[7] == num[8] == num[9]:
        num = str(random.randint(10 ** 9, 10 ** 10 - 1))
    return "(" + num[:3] + ")" + num[3:6] + "-" + num[6:]


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
    if sys.argv[1] not in ("user", "member"):
        raise ValueError("first parameter must be 'user' or 'member'")

    test = False
    if len(sys.argv) > 3 and sys.argv[3] == "-t":
        test = True

    try:
        producer = DataProducer(sys.argv[1], int(sys.argv[2]))
        if sys.argv[1] == "user":
            data_generator = producer.user_generator(test=test)
        else:
            data_generator = producer.member_generator(test=test)
        producer.records_insert(data_generator)

    except Exception as err:
        print(err)

    finally:
        if test:
            print("testing inserted records...")
            # producer.verify_records()


if __name__ == "__main__":
    main()
