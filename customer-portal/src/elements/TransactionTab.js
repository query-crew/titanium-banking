import React, { useEffect, useState } from "react";
import TransactionList from "../components/TransactionList";
import TransactionService from "../services/TransactionService";
import LoadingSpinner from "../atoms/LoadingSpinner";

import SearchBar from "../atoms/SearchBar";
import TransactionSortForm from "../atoms/TransactionSortForm";
import CustomPagination from "../atoms/CustomPagination";

const TransactionTab = (props) => {
  const accountNumber = props.accountNumber;
  const [isLoading, setLoading] = useState(true);
  const [transactions, setTransactions] = useState([]);
  const [searchInput, setSearchInput] = useState("");
  const [sortProp, setSortProp] = useState("");
  const [pageInfo, setPageInfo] = useState({});
  const [pageSize, setPageSize] = useState(8);
  const [params, setParams] = useState(
    TransactionService.getRequestParams("", "", 0, 8)
  );
  useEffect(() => {
    setLoading(true);
    TransactionService.getTransactions(
      accountNumber,
      params,
      setTransactions,
      setLoading,
      setPageInfo
    );
  }, [accountNumber, params]);

  const handleSearch = () => {
    //0 to set search to page 1. could be changed to stay on cur page but might cause issue if the cur page does not exist
    const curSortProp = params.sortProp != null ? params.sortProp : "";
    setSearchInput(searchInput.trim());
    const newParams = TransactionService.getRequestParams(
      searchInput.trim(),
      curSortProp,
      0,
      pageSize
    );
    console.log(newParams, "handle search");
    setParams(newParams);
  };

  const handleSort = () => {
    const curSearch = params.description != null ? params.description : "";
    const newParams = TransactionService.getRequestParams(
      curSearch,
      sortProp,
      0,
      pageSize
    );
    console.log(newParams, "handle sort");
    setParams(newParams);
  };

  const handlePageChange = (newPage) => {
    if (
      newPage !== pageInfo.curPage &&
      newPage < pageInfo.totalPages &&
      newPage >= 0
    ) {
      console.log(pageInfo);
      const curSearch = params.description != null ? params.description : "";
      const curSortProp = params.sortProp != null ? params.sortProp : "";
      const newParams = TransactionService.getRequestParams(
        curSearch,
        curSortProp,
        newPage,
        pageSize
      );
      setParams(newParams);
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
        <div className="fs-4">Transactions</div>
      </div>

      {!isLoading ? (
        <>
          <TransactionList
            transactions={transactions}
            accountNumber={accountNumber}
          />
          <CustomPagination
            pageInfo={pageInfo}
            handlePageChange={handlePageChange}
          />
        </>
      ) : (
        <LoadingSpinner />
      )}
    </>
  );
};
export default TransactionTab;
