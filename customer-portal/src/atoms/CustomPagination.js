import React from "react";
import Pagination from "react-bootstrap/Pagination";

const CustomPagination = (props) => {
  const pageInfo = props.pageInfo;

  return (
    <Pagination className="justify-content-center my-2">
      <Pagination.First
        disabled={pageInfo.curPage === 0 || pageInfo.totalPages === 0}
        onClick={() => props.handlePageChange(0)}
      />
      <Pagination.Prev
        disabled={pageInfo.curPage === 0 || pageInfo.totalPages === 0}
        onClick={() => props.handlePageChange(pageInfo.curPage - 1)}
      />
      <Pagination.Item>{1}</Pagination.Item>
      <Pagination.Item>{2}</Pagination.Item>
      <Pagination.Item>{3}</Pagination.Item>
      <Pagination.Next
        disabled={
          pageInfo.curPage === pageInfo.totalPages - 1 ||
          pageInfo.totalPages === 0
        }
        onClick={() => props.handlePageChange(pageInfo.curPage + 1)}
      />
      <Pagination.Last
        disabled={
          pageInfo.curPage === pageInfo.totalPages - 1 ||
          pageInfo.totalPages === 0
        }
        onClick={() => props.handlePageChange(pageInfo.totalPages - 1)}
      />
    </Pagination>
  );
};

export default CustomPagination;
