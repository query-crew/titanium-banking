import pytest
import types
import re
import requests
from data_producer import DataProducer


class TestUserGenerator:
    def test_gen_type(self):
        producer = DataProducer("user", 1)
        generator = producer.user_generator(test=True)
        assert isinstance(generator, types.GeneratorType)
        assert isinstance(next(generator), dict)
        with pytest.raises(StopIteration):
            next(generator)

    def test_gen_regex(self):
        producer = DataProducer("user", 1)
        generator = producer.user_generator(test=True)
        data = next(generator)
        assert data["userType"] == "admin"
        assert re.match("[a-zA-Z]+", data["username"])
        assert data["password"]
        assert data["email"]


class TestMemberGenerator:
    def test_gen_type(self):
        producer = DataProducer("member", 1)
        generator = producer.member_generator(test=True)
        assert isinstance(generator, types.GeneratorType)
        assert isinstance(next(generator), dict)
        with pytest.raises(StopIteration):
            next(generator)

    def test_gen_regex(self):
        producer = DataProducer("member", 1)
        generator = producer.member_generator(test=True)
        data = next(generator)
        assert len(data) == 12


class TestRecordsInsert:
    def generator(self):
        for i in range(1):
            data = {}
            data["userType"] = "admin"
            data["username"] = "testuser"
            data["email"] = "testuser@gmail.com"
            data["password"] = "short"
            yield data

    def test_insert(self):
        generator = self.generator()
        producer = DataProducer("user", 1)
        assert producer.records_insert(generator)


class TestVerifyRecords:
    def test_login(self):
        producer = DataProducer("user", 1)
        data = {"username": "testuser", "password": "short"}
        producer.records_produced = [data]
        with pytest.raises(requests.exceptions.HTTPError):
            # Cannot verify disabled accounts currently
            producer.verify_records()

    def test_fail_login(self):
        producer = DataProducer("user", 1)
        data = {"username": "testuser", "password": "incorrectpass"}
        producer.records_produced = [data]
        with pytest.raises(Exception):
            producer.verify_records()
