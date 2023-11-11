import { useState, useEffect } from "react";
import { useSearchParams } from "react-router-dom";
import { API } from "../../Config/APIConfig";
import axios from "axios";

import { Row, Col } from "react-bootstrap";
import NavigationBar from "../../Component/NavigationBar/NavigationBar";
import SearchEbookCard from "./SearchCard/SearchEbookCard";
import SearchMenuCard from "./SearchCard/SearchMenuCard";
import SearchVoiceCard from "./SearchCard/SearchVoiceCard";

import classes from "./SearchPage.module.css";
import { Container } from "react-bootstrap";

export default function SearchPage(props) {
  const [searchParams, setSearchParams] = useSearchParams();
  const searchKeyword = searchParams.get("keyword");
  const [searchedProducts, setSearchedProducts] = useState([]);
  const [searchedTTS, setSearchedTTS] = useState([]);
  const [menuForm, setMenuForm] = useState({
    selectedEbook: true,
    selectedTTS: true,
    searchStd: 'title'
  });
  var api = `${API.SEARCH_EBOOK_NAME}`;
  useEffect(() => {
    // baseAPI setting
    console.log(menuForm);
    switch (menuForm.searchStd) {
      case 'title': api = (API.SEARCH_EBOOK_NAME); break;
      case 'author': api = (API.SEARCH_EBOOK_AUTHOR); break;
      case 'publisher': api = (API.SEARCH_EBOOK_PUBLISHER); break;
      default: console.log('baseAPI setting 오류'); break;
    }
    

    if (menuForm.selectedEbook) {
      console.log(api);
      axios
        .get(`${api}${searchKeyword}`)
        .then((res) => {
          setSearchedProducts(res.data.content);
        })
        .catch((err) => {
          console.log(err);
        });
    }
    else{
      // Ebook 데이터 초기화
      setSearchedProducts([]);
    }
    if (menuForm.selectedTTS) {
      // TTS 검색기록
      axios.get(`${API.SEARCH_VOICE}${searchKeyword}`)
      .then((res)=>{
        setSearchedTTS(res.data.content);
      })
    }
  }, [searchKeyword, menuForm]);
  const ebookCard = searchedProducts.map((book, idx) => {
    return (
      <SearchEbookCard key={idx} prd={book}>
        {book.ebookName}
      </SearchEbookCard>
    );
  });
  const voiceCard = searchedTTS.map((voice, idx)=>{
    return(
      <SearchVoiceCard key = {idx} prd = {voice}>
        </SearchVoiceCard>
    )
  })
  return (
    <>
      <NavigationBar keyword = {searchKeyword} />
      <div style={{ width: "100%", height: "6rem" }} />
      <div className={classes["search-title"]}>
        '<span className={classes.target}>{searchKeyword}</span>'에 대한 {searchedProducts.length}개의 검색결과
      </div>
      <Container>
        <Row>
          <Col><SearchMenuCard setMenu={setMenuForm} /></Col>
          {(ebookCard.length === 0 && voiceCard.length === 0) ?<Col md = {9}><div className = {classes['no-result']}>검색된 결과가 없음</div></Col> : <Col md={9}>{ebookCard}{voiceCard}</Col> }
        </Row>
      </Container>
    </>
  );
}
