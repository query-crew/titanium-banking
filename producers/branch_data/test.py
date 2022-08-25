from branch_producer import BranchProducer
import types
import requests
import pytest


class TestBranchGenerator:
    def test_gen_type(self):
        producer = BranchProducer(1)
        generator = producer.branch_generator()
        assert isinstance(generator, types.GeneratorType)
        assert isinstance(next(generator), dict)
        with pytest.raises(StopIteration):
            next(generator)

    def test_gen_data(self):
        producer = BranchProducer(1)
        generator = producer.branch_generator()
        data = next(generator)
        assert data["branchName"]
        assert data["addressLine1"]
        assert data["addressLine2"]
        assert data["city"]
        assert data["state"]
        assert data["zipCode"]
        assert data["branchDetails"]


class TestVerifyInsert:
    def test_insert(self):
        producer = BranchProducer(10)
        try:
            producer.records_insert()
        except Exception:
            return False
        total_start = int(producer.get_all_branches()["totalItems"])
        producer.verify_records(total_start)


class TestDelete:
    def test_delete_all(self):
        producer = BranchProducer()
        assert producer.delete_all_branches() == 200


class TestGetAll:
    def test_get_all(self):
        producer = BranchProducer()
        assert isinstance(producer.get_all_branches(), dict)


class TestInsert:
    def generator(self, n):
        for _ in range(n):
            data = {}
            data["branchName"] = "testname"
            data["addressline1"] = "atestaddress"
            data["city"] = "atestcity"
            data["state"] = "TS"
            data["zipCode"] = 55555
            data["branchDetails"] = "sometestdetails"
            yield data

    def test_insert(self):
        producer = BranchProducer()
        generator = self.generator(1)
        total_start = int(producer.get_all_branches()["totalItems"])
        try:
            producer.records_insert(generator)
        except Exception:
            return False
        total_end = int(producer.get_all_branches()["totalItems"])
        assert total_end - total_start == 1
