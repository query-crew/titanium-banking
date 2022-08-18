""" """
import sys
import os
from dotenv import load_dotenv
from faker import Faker
import requests
import random
import json
import datetime


class TransactionProducer:
    """
    """

    def __init__(self, n_records):
        self.accounts = set()  # where to store account ids
        self.n_records = int(n_records)

        load_dotenv()
        self.auth_header = os.getenv("AUTH_HEADER")
        self.base_url = os.getenv("BASE_URL")
        self.transaction_url = os.getenv("TRANSACTION_ROUTE")
        self.from_acc_url = os.getenv("FROM_ACCOUNT_ROUTE")
        self.to_acc_url = os.getenv("TO_ACCOUNT_ROUTE")
        self.transaction_type_url = os.getenv("TRANSACTION_TYPE_ROUTE")
        self.date_url = os.getenv("DATE_ROUTE")

        self.transaction_types = os.getenv("TRANSACTION_TYPES")

    def get_available_accounts(self):
        """ not currently possible, but can maybe simulate """
        for i in range(1, 100):  # so like 99 accounts
            self.accounts.add(i)

    def get_random_account(self, not_id=None):
        """ get a random id from accs that is not equal to passed in id """
        if len(self.accounts) < 1:
            self.get_available_accounts()

        rand_id = not_id
        while rand_id == not_id:
            rand_id = random.randint(1, (len(self.accounts) - 1))
        return rand_id

    def get_balance(self, id=None):
        """ get balance of an account """
        # cannot be implemented yet
        pass

    def get_all(self):
        """ return all transactions from the database """
        url = self.base_url + self.transaction_url
        try:
            headers = {"Authorization": self.auth_header}
            resp = requests.request("GET", url, headers=headers)
            if resp.status_code == 401:
                raise requests.exceptions.HTTPError(
                    f"{resp.status_code} unauthorized request"
                )
            return resp.json()
        except requests.exceptions.HTTPError as err:
            print(err)
            return False

    def transaction_generator(self):
        fake = Faker()
        transaction_types = {
            1: "Purchase",  # Debit
            2: "Purchase",  # Credit
            3: "Transfer",  # Transfer
        }
        for _ in range(self.n_records):
            transaction_type = random.randint(1, 3)
            date = datetime.datetime.fromtimestamp(fake.unix_time())
            description = fake.company() + " " + transaction_types[transaction_type]
            amount = random.randrange(0, 100000, 5)  # 0-100000, step of 5
            from_id = self.get_random_account()
            to_id = self.get_random_account(not_id=from_id)
            transaction_dict = {
                "transactionType": transaction_type,
                "transactionDate": int(round(date.timestamp() * 1000)),
                "description": description,
                "amount": amount,
                "accountFromId": from_id,
                "accountToId": to_id,
            }
            print(type(date))
            print(json.dumps(transaction_dict))
            yield transaction_dict

    def records_insert(self, generator):
        """ read generator and insert data into database """
        url = self.base_url + self.transaction_url
        headers = {
            "Authorization": self.auth_header,
            "Content-Type": "application/json",
            "Accept": "*/*",
            "Accept-Encoding": "gzip, deflate, br",
            "Connection": "keep-alive",
        }
        try:
            while True:
                data = next(generator)
                if data is not None:
                    print(url)
                    resp = requests.request("POST", url, headers=headers, json=data)
                    if resp.status_code != 201:
                        raise requests.exceptions.HTTPError(
                            f"Error: Status code {resp.status_code}"
                        )

        except requests.exceptions.HTTPError as err:
            print(err)
            return False


def main():
    if len(sys.argv) < 2:
        raise ValueError(
            "Please enter input formatted as \n \
            python transaction_data.py -n [number of records to insert] \
        "
        )
    try:
        if "-n" in sys.argv:
            try:
                num_index = sys.argv.index("-n") + 1
                producer = TransactionProducer(sys.argv[num_index])
            except IndexError as err:
                print(f"Error: {err} \n You must enter amount after '-n'")
                return
            generator = producer.transaction_generator()
            producer.records_insert(generator)
    except StopIteration:
        print("", end="")


if __name__ == "__main__":
    main()
