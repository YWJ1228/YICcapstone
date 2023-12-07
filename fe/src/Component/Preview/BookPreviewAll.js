import { useEffect, useState } from "react";
import { API } from "../../Config/APIConfig";
import { PageConfig, DebuggingMode } from "../../Config/Config";

import axios from "axios";

import { Container, Row, Col, Stack, Form, Button, ButtonGroup } from "react-bootstrap";

import NativeSelect from "@mui/material/NativeSelect";

import classes from "./BookPreviewAll.module.css";
import BookPreview from "./BookPreview";

export default function BookPreviewAll(props) {
  const [currentCategory, setCurrentCategory] = useState("all");
  const [currentPage, setCurrentPage] = useState(1);
  const [pageCnt, setPageCnt] = useState(1);
  const [entireBook, setEntireBook] = useState([PageConfig.EBOOK_PAGE_DEFAULT_STATE]);
  const [sortType, setSortType] = useState("0");
  useEffect(() => {
    var api = `${API.LOAD_CATEGORY_EBOOKS_UPLOAD}${currentCategory}&page=${currentPage - 1}`;
    if (currentCategory === "all") {
      switch (sortType) {
        case "0":
          api = `${API.LOAD_EBOOK_SORTBY_UPLOAD}${currentPage - 1}`;
          break;
        case "1":
          api = `${API.LOAD_EBOOK_SORTBY_POPULARITY}${currentPage - 1}`;
          break;
        case "2":
          api = `${API.LOAD_EBOOK_SORTBY_LOW_PRICE}${currentPage - 1}`;
          break;
        case "3":
          api = `${API.LOAD_EBOOK_SORTBY_HIGH_PRICE}${currentPage - 1}`;
          break;
      }
    } else {
      switch (sortType) {
        case "0":
          api = `${API.LOAD_CATEGORY_EBOOKS_UPLOAD}${currentCategory}&page=${currentPage - 1}`;
          break;
        case "1":
          api = `${API.LOAD_CATEGORY_EBOOKS_SORTBY_POPULARITY}${currentCategory}&page=${currentPage - 1}`;
          break;
        case "2":
          api = `${API.LOAD_CATEGORY_EBOOKS_SORTBY_LOW_PRICE}${currentCategory}&page=${currentPage - 1}`;
          break;
        case "3":
          api = `${API.LOAD_CATEGORY_EBOOKS_SORTBY_HIGH_PRICE}${currentCategory}&page=${currentPage - 1}`;
          break;
      }
    }
    axios
      .get(api)
      .then((res1) => {
        const resData = res1.data.content.map((book) => ({
          id: book.id,
          image: book.imageUrl,
          name: book.ebookName,
          author: book.author,
          price: book.price,
        }));
        setEntireBook(resData);
        setPageCnt(res1.data.totalPages); // page 개수 가져오기
        DebuggingMode([`${currentCategory} 페이지 `, `${currentCategory} 페이지 수`], [resData, res1.data.totalPages]);
      })
      .catch((err) => {
        // console.log(err);
      });
  }, [currentPage, currentCategory, sortType]);

  // category 변경하면 항상 첫페이지로 넘어가도록
  function CategoryClickHandler(category) {
    setCurrentCategory(category);
    setCurrentPage(1);
  }
  function SortTypeChangeHandler(event) {
    setCurrentPage(1);
    setSortType(event.target.value);
  }
  // 카테고리 리스트 시각화
  const catergoryArr = PageConfig.EBOOK_CATEGORY_LIST.map((category, idx) => {
    return (
      <Button
        key={idx}
        className={currentCategory !== category ? classes.category : classes["category-focus"]}
        onClick={() => {
          CategoryClickHandler(category);
        }}
      >
        {category}
      </Button>
    );
  });
  // 페이지 버튼 배열
  const buttonArr = Array.from({ length: pageCnt }, (v, i) => i + 1).map((pageNum, idx) => {
    return (
      <Button
        key={idx}
        className={pageNum === currentPage ? classes["page-button-focus"] : classes["page-button"]}
        onClick={() => {
          setCurrentPage(pageNum);
        }}
      >
        {pageNum}
      </Button>
    );
  });
  const selectList = PageConfig.SORT_TYPE.map((type, idx) => {
    return (
      <option value={idx} key={idx}>
        {type}
      </option>
    );
  });

  return (
    <Container>
      <Row className={classes.title}>{props.title}</Row>
      <Row className={classes["category-wrapper"]}>
        <Col>
          <Stack direction="horizontal" gap={3}>
            {catergoryArr}
          </Stack>
        </Col>

        <Col className={classes.selectBox}>
          <NativeSelect defaultValue={0} onChange={SortTypeChangeHandler}>
            {selectList}
          </NativeSelect>
        </Col>
      </Row>
      <hr></hr>
      <Row>
        <BookPreview images={entireBook} once={true} />
      </Row>
      <Row>
        <Col className={classes["button-wrapper"]}>
          <ButtonGroup>{buttonArr}</ButtonGroup>
        </Col>
      </Row>
    </Container>
  );
}
