import pytest
import types
import requests
from transaction_producer import TransactionProducer


class TestTransactionGenerator:
    def test_gen_type(self):
        producer = TransactionProducer(1)
        generator = producer.transaction_generator()
        assert isinstance(generator, types.GeneratorType)
        assert isinstance(next(generator), dict)
        with pytest.raises(StopIteration):
            next(generator)

    def test_gen_data(self):
        producer = TransactionProducer(1)
        generator = producer.transaction_generator()
        data = next(generator)
        assert isinstance(data["transactionType"], int)
        assert isinstance(data["transactionDate"], int)
        assert isinstance(data["description"], str)
        assert isinstance(data["amount"], int)
        assert isinstance(data["accountFromId"], int)
        assert isinstance(data["accountToId"], int)


class TestGetAvailableAccounts:
    def test_get(self):
        producer = TransactionProducer(1)
        producer.get_available_accounts()
        assert len(producer.accounts) > 0


class TestGetRandomAcc:
    def test_get(self):
        producer = TransactionProducer(1)
        producer.get_available_accounts()
        n_to_not_equal = next(iter(producer.accounts))
        for i in range(len(producer.accounts) * 10000):
            assert producer.get_random_account(n_to_not_equal) != n_to_not_equal


class TestGetBalance:
    def test_get(self):
        pass  # to be implemented later


class TestGetAll:
    def test_get(self):
        producer = TransactionProducer(1)
        resp = producer.get_all()
        assert isinstance(resp, dict)


class TestRecordsInsert:
    def test_insert(self):
        producer = TransactionProducer(1)
        generator = producer.transaction_generator()
        assert producer.records_insert(generator)
