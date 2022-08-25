import axios from "axios";

const url = process.env.REACT_APP_API_URL;

const TransactionService = {
  getTransactions: function (
    accountNumber,
    params,
    updateTransactions,
    updateLoading,
    updatePage
  ) {
    axios(url + "/transaction/account/" + accountNumber, {
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
  getRequestParams: function (description, sortProp, page, size) {
    const params = {
      description: description && description.length > 0 ? description : null,
      sortProp: sortProp && sortProp.length > 0 ? sortProp : null,
      page: page !== undefined ? page : null,
      size: size !== undefined ? size : null,
    };
    return params;
  },
};

export default TransactionService;
