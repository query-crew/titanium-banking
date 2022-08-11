import React, { useEffect, useState } from "react";
import TransactionList from "../components/TransactionList";
import TransactionService from "../services/TransactionService";
import LoadingSpinner from "../atoms/LoadingSpinner";

import SearchBar from "../atoms/SearchBar";
import TransactionSortForm from "../atoms/TransactionSortForm";
import CustomPagination from "../atoms/CustomPagination";

const TransactionTab = (props) => {
  const accountNumber = props.accountNumber;
  const [isLoadingFrom, setLoadingFrom] = useState(true);
  const [transactionsFrom, setTransactionsFrom] = useState([]);
  const [isLoadingTo, setLoadingTo] = useState(true);
  const [transactionsTo, setTransactionsTo] = useState([]);
  const [searchInput, setSearchInput] = useState("");
  const [sortProp, setSortProp] = useState("");
  const [fromPage, setFromPage] = useState({});
  const [toPage, setToPage] = useState({});

  useEffect(() => {
    setLoadingFrom(true);
    setLoadingTo(true);
    const params = {};
    TransactionService.getTransactionsFrom(
      accountNumber,
      params,
      setTransactionsFrom,
      setLoadingFrom,
      setFromPage
    );
    TransactionService.getTransactionsTo(
      accountNumber,
      params,
      setTransactionsTo,
      setLoadingTo,
      setToPage
    );
  }, [accountNumber]);

  const handleSearch = () => {
    //0 to set search to page 1. could be changed to stay on cur page but might cause issue if the cur page does not exist
    const paramsFrom = TransactionService.getRequestParams(
      searchInput,
      sortProp,
      0
    );
    const paramsTo = TransactionService.getRequestParams(
      searchInput,
      sortProp,
      0
    );
    setLoadingFrom(true);
    setLoadingTo(true);
    TransactionService.getTransactionsFrom(
      accountNumber,
      paramsFrom,
      setTransactionsFrom,
      setLoadingFrom,
      setFromPage
    );
    TransactionService.getTransactionsTo(
      accountNumber,
      paramsTo,
      setTransactionsTo,
      setLoadingTo,
      setToPage
    );
  };

  const handleSort = () => {
    const paramsFrom = TransactionService.getRequestParams(
      searchInput,
      sortProp,
      0
    );
    const paramsTo = TransactionService.getRequestParams(
      searchInput,
      sortProp,
      0
    );
    setLoadingFrom(true);
    setLoadingTo(true);
    TransactionService.getTransactionsFrom(
      accountNumber,
      paramsFrom,
      setTransactionsFrom,
      setLoadingFrom,
      setFromPage
    );
    TransactionService.getTransactionsTo(
      accountNumber,
      paramsTo,
      setTransactionsTo,
      setLoadingTo,
      setToPage
    );
  };

  const handleFromPageChange = (newPage) => {
    if (
      newPage !== fromPage.curPage &&
      newPage < fromPage.totalPages &&
      newPage >= 0
    ) {
      setLoadingFrom(true);
      const params = TransactionService.getRequestParams(
        searchInput,
        sortProp,
        newPage
      );
      TransactionService.getTransactionsFrom(
        accountNumber,
        params,
        setTransactionsFrom,
        setLoadingFrom,
        setFromPage
      );
    }
  };

  const handleToPageChange = (newPage) => {
    if (
      newPage !== toPage.curPage &&
      newPage < toPage.totalPages &&
      newPage >= 0
    ) {
      setLoadingTo(true);
      const params = TransactionService.getRequestParams(
        searchInput,
        sortProp,
        newPage
      );
      TransactionService.getTransactionsTo(
        accountNumber,
        params,
        setTransactionsTo,
        setLoadingTo,
        setToPage
      );
    }
  };
  return (
    <>
      <SearchBar setSearch={setSearchInput} handleSubmit={handleSearch} />
      <TransactionSortForm
        setSortProp={setSortProp}
        handleSubmit={handleSort}
      />
      <div className="d-flex justify-content-between align-items-center">
        <div className="fs-4">Transactions Made </div>
      </div>

      {!isLoadingFrom ? (
        <>
          <TransactionList transactions={transactionsFrom} from={true} />
          <CustomPagination
            pageInfo={fromPage}
            handlePageChange={handleFromPageChange}
          />
        </>
      ) : (
        <LoadingSpinner />
      )}
      <hr />
      <div className="d-flex justify-content-between align-items-center">
        <div className="fs-4">Transactions Received </div>
      </div>
      {!isLoadingTo ? (
        <>
          <TransactionList transactions={transactionsTo} from={false} />
          <CustomPagination
            pageInfo={toPage}
            handlePageChange={handleToPageChange}
          />
        </>
      ) : (
        <LoadingSpinner />
      )}
    </>
  );
};
export default TransactionTab;
