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
