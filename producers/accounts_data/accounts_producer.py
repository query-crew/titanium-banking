""" Accounts dummy data producer, makes fake accounts for testing """
import sys
import os
import datetime
import json
from dotenv import load_dotenv
from faker import Faker
import requests


class AccountsProducer:
    """
    """

    def __init__(self, n_records=0):
        self.n_records = int(n_records)
        load_dotenv()
        self.base_url = os.getenv("BASE_URL")
        self.login_url = os.getenv("LOGIN_URL")
        self.admin_user = os.getenv("ADMIN_USER")
        self.admin_pass = os.getenv("ADMIN_PASS")
        self.crt_path = os.getenv("CRT_PATH")
        self.auth_header = os.getenv("AUTH_HEADER")
        self.account_route = os.getenv("ACCOUNT_ROUTE")
        self.account_type_route = os.getenv("ACCOUNT_TYPE_ROUTE")

    def __repr__(self):
        return f"Accounts Producer Object: {self.n_records} records"

    def login_admin(self):
        url = self.login_url
        admin_user = self.admin_user
        admin_pass = self.admin_pass
        self.session = requests.Session()
        data = {"username": admin_user, "password": admin_pass}
        try:
            resp = self.session.post(url, json=data, verify=self.crt_path)

        except requests.exceptions.HTTPError as err:
            print(err, resp.status_code)
            return False
        return True

    def delete_all_accounts(self):
        """
        method used to clean accounts for generation or
        just to reset them all
        """
        url = self.base_url + self.account_route
        try:
            resp = self.session.delete(url, verify=self.crt_path)
        except requests.exceptions.HTTPError as err:
            print(err, resp.status_code)
            return False
        return True

    def list_all_accounts(self):
        """
        method used to query micro service for all active accounts
        """
        pages = "?pageNo=0&pageSize=10000"
        url = self.base_url + self.account_route + pages
        try:
            resp = self.session.get(url, verify=self.crt_path)
            print(json.dumps(json.loads(resp.content), indent=2))
        except requests.exceptions.HTTPError as err:
            print(err, resp.status_code)
            return False
        return True

    def save_available_account_types(self):
        """
        Query about all available account types
        to be used in creating valid accounts
        """
        url = self.base_url + self.account_type_route
        try:
            resp = self.session.get(url, verify=self.crt_path)
            account_types_dict = dict(json.loads(resp.content))
            self.account_types_dict = {}
            for i in account_types_dict["accountTypes"]:
                self.account_types_dict[i["accountTypeId"]] = {
                    "accountId": i["accountTypeId"],
                    "balance_requirement": i["balanceRequirement"],
                }
        except requests.exceptions.HTTPError as err:
            print(err, resp.status_code)
            return False
        return True

    def accounts_generator(self):
        """
        using faker library create dummy data for the accounts micro service
        returns a generator object
        """

        fake = Faker()
        for _ in range(self.n_records):
            date = datetime.datetime.fromtimestamp(fake.unix_time())
            date = date.replace(hour=0, minute=0, second=0, microsecond=0)
            date = str(date).split()[0]
            enabled = 1
            member_id = fake.random_int(min=1, max=50)
            account_type_index = fake.random_int(
                min=1, max=len(self.account_types_dict)
            )
            account_type_id = self.account_types_dict[account_type_index]["accountId"]
            min_bal = self.account_types_dict[account_type_index]["balance_requirement"]
            balance = fake.random_int(min=min_bal, max=10000)

            account_dict = {
                "balance": balance,
                "lastStatementDate": str(date),
                "enabled": enabled,
                "memberId": member_id,
                "accountTypeId": account_type_id,
            }
            yield account_dict

    def records_insert(self, generator):
        """ send records generated to POST endpoint """
        url = self.base_url + self.account_route
        headers = {"Content-Type": "application/json"}

        try:
            while True:
                data = next(generator)
                if data is not None:
                    resp = self.session.post(
                        url, headers=headers, json=data, verify=self.crt_path
                    )
        except requests.exceptions.HTTPError as err:
            print(err, resp.status_code)


def main():
    """
    driver that will parse console arguments
    """

    if len(sys.argv) < 2:
        raise ValueError(
            "Please enter input formatted as: \n \
            python accounts_producers [flags]"
        )
    try:
        if "-n" in sys.argv:
            num_index = sys.argv.index("-n") + 1
            producer = AccountsProducer(sys.argv[num_index])
        else:
            producer = AccountsProducer()

        if not producer.login_admin():
            raise Exception("Error occurred logging in as admin")

        if "-d" in sys.argv:
            # delete all accounts
            producer.delete_all_accounts()

        if producer.n_records != 0:
            # last thing is to insert records
            producer.save_available_account_types()
            generator = producer.accounts_generator()
            producer.records_insert(generator)

    except Exception as err:
        print(err)

    finally:
        if "-l" in sys.argv:
            # list all accounts
            producer.list_all_accounts()


if __name__ == "__main__":
    main()
