import { useState, useEffect } from "react";
import { useSearchParams } from "react-router-dom";
import { API } from "../../Config/APIConfig";
import axios from "axios";

import { Row, Col } from "react-bootstrap";
import NavigationBar from "../../Component/NavigationBar/NavigationBar";
import SearchCard from "./SearchCard/SearchCard";
import SearchMenuCard from "./SearchCard/SearchMenuCard";

import classes from "./SearchPage.module.css";
import { Container } from "react-bootstrap";

export default function SearchPage(props) {
  const [searchParams, setSearchParams] = useSearchParams();
  const searchKeyword = searchParams.get("keyword");
  const [searchedProducts, setSearchedProducts] = useState([]);
  console.log(searchedProducts);
  useEffect(() => {
    axios
      .get(`${API.SEARCH_EBOOK}${searchKeyword}`)
      .then((res) => {
        setSearchedProducts(res.data.content);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [searchKeyword]);
  const card = searchedProducts.map((book, idx) => {
    console.log(book);
    return (
        <SearchCard key={idx} prd={book}>
          {book.ebookName}
        </SearchCard>
    );
  });
  return (
    <>
      <NavigationBar />
      <div style={{ width: "100%", height: "6rem" }} />
      <div className={classes["search-title"]}>
        '<span className={classes.target}>{searchKeyword}</span>'에 대한 {searchedProducts.length}개의 검색결과
      </div>
      <Container>
        <Row>
          <Col><SearchMenuCard/></Col>
          <Col md={9}>{card}</Col>
        </Row>
      </Container>
    </>
  );
}
