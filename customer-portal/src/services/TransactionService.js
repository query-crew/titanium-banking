import axios from "axios";

const url = process.env.REACT_APP_API_URL;

const TransactionService = {
  getTransactionsFrom: function (
    accountNumber,
    params,
    updateTransactions,
    updateLoading,
    updatePage
  ) {
    axios(url + "/transaction/fromAccount/" + accountNumber, {
      method: "GET",
      auth: {
        username: "jordan",
        password: "password",
      },
      params: { ...params },
    })
      .then((res) => {
        console.log(res.data);
        const transactions = res.data.transactions;
        updateTransactions(transactions);
        updateLoading(false);
        const pageInfo = {
          curPage: res.data.currentPage,
          totalItems: res.data.totalItems,
          totalPages: res.data.totalPages,
        };
        updatePage(pageInfo);
      })
      .catch((err) => console.log(err));
  },
  getTransactionsTo: function (
    accountNumber,
    params,
    updateTransactions,
    updateLoading,
    updatePage
  ) {
    axios(url + "/transaction/toAccount/" + accountNumber, {
      method: "GET",
      auth: {
        username: "jordan",
        password: "password",
      },
      params: { ...params },
    })
      .then((res) => {
        const transactions = res.data.transactions;
        updateTransactions(transactions);
        updateLoading(false);
        const pageInfo = {
          curPage: res.data.currentPage,
          totalItems: res.data.totalItems,
          totalPages: res.data.totalPages,
        };
        updatePage(pageInfo);
      })
      .catch((err) => console.log(err));
  },
  getRequestParams: function (description, sortProp, page, size) {
    let params = {};
    if (description && description.length > 0) {
      params[`description`] = description;
    }
    if (sortProp && sortProp.length > 0) {
      params[`sortProp`] = sortProp;
    }
    if (page) {
      params[`page`] = page;
    }
    if (size) {
      params[`size`] = size;
    }
    return params;
  },
};

export default TransactionService;
