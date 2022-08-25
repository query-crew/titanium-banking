import pytest
import types
import requests
from accounts_producer import AccountsProducer


class TestLogin:
    def test_login(self):
        producer = AccountsProducer()
        assert producer.login_admin()


class TestDelete:
    def test_delete(self):
        producer = AccountsProducer()
        assert producer.login_admin()  # must login to delete
        assert producer.delete_all_accounts()


class TestListAll:
    def test_list(self):
        producer = AccountsProducer()
        assert producer.login_admin()  # must login to list
        assert producer.list_all_accounts()


class TestSaveAvailableAccounts:
    def test_save_account_types(self):
        producer = AccountsProducer()
        assert producer.login_admin()  # must login to save accounts
        assert producer.save_available_account_types()
        assert producer.account_types_dict


class TestAccountGenerator:
    def test_gen_type(self):
        producer = AccountsProducer(1)
        generator = producer.accounts_generator()
        assert producer.login_admin()  # must login to save available accs
        assert (
            producer.save_available_account_types()
        )  # must save accs to generate valid ones
        assert isinstance(generator, types.GeneratorType)
        assert isinstance(next(generator), dict)
        with pytest.raises(StopIteration):
            next(generator)

    def test_gen_data(self):
        producer = AccountsProducer(1)
        generator = producer.accounts_generator()
        assert producer.login_admin()  # must login to save available accs
        assert (
            producer.save_available_account_types()
        )  # must save accs to generate valid ones
        assert isinstance(generator, types.GeneratorType)
        data = next(generator)
        assert len(data) == 5
        assert data["balance"]
        assert data["lastStatementDate"]
        assert data["enabled"] == 1
        assert data["memberId"]
        assert data["accountTypeId"] in producer.account_types_dict


class TestInsert:
    def generator(self):
        for _ in range(1):
            data = {
                "balance": 5000,
                "lastStatementDate": "1980-12-10",
                "enabled": 1,
                "memberId": 1,
                "accountTypeId": 1,
            }
            yield data

    def test_insert(self):
        producer = AccountsProducer(1)
        generator = self.generator()
        assert producer.login_admin()  # must login as admin to add accounts
        with pytest.raises(StopIteration):
            assert producer.records_insert(generator)
