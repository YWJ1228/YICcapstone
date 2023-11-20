import { useState, useEffect } from "react";
import { PageConfig } from "../../Config/Config";
import { getCookies } from "../../Component/Cookies/LoginCookie";
import { API } from "../../Config/APIConfig";
import axios from "axios";

import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Box from "@mui/material/Box";

import PurchaseBook from "./PurchaseBook";
import LikeList from "./LikeList";
import MyPageReview from "./MyPageReview";

import classes from "./MyPageMenu.module.css";

export default function MyPageMenu() {
  const [value, setValue] = useState(0);
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const [bookList, setBookList] = useState([PageConfig.EBOOK_PAGE_DEFAULT_STATE]);
  const [reviewList, setReviewList] = useState([]);
  const [likeList, setLikeList] = useState([PageConfig.EBOOK_PAGE_DEFAULT_STATE]);

  useEffect(() => {
    axios
      .all([
        axios.get(API.LOAD_PURCHASED_EBOOKS, {
          headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
        }),
        axios.get(API.LOAD_REVIEW_NOT_WRITTEN_EBOOKS, {
          headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
        }),
        axios.get(API.LOAD_MYPAGE_LIKELIST),
      ])
      .then(
        axios.spread((res1, res2, res3) => {
          console.log(res1, res2);
          const resData1 = res1.data.content.map((book) => ({
            id: book.purchaseId,
            image: book.ebook.imageUrl,
            name: book.ebook.ebookName,
            author: book.ebook.author,
            price: book.ebook.price,
          }));
          setBookList(resData1);
          const resData2 = res2.data.content.map((book) => ({
            id: book.purchaseId,
            image: book.ebook.imageUrl,
            name: book.ebook.ebookName,
            author: book.ebook.author,
            price: book.ebook.price,
          }));
          setReviewList(resData2);
          const resData3 = res3.data.content.map((book) => ({
            id: book.id,
            image: book.imageUrl,
            name: book.ebookName,
            author: book.author,
          }));
          setLikeList(resData3);
        })
      )
      .catch((err) => console.log(err));
  }, []);

  return (
    <Box className={classes["tab-wrapper"]}>
      <Box sx={{ borderBottom: 1, borderColor: "divider" }} className={classes["nav-wrapper"]}>
        <Tabs value={value} aria-label="basic tabs example" onChange={handleChange}>
          <Tab className={classes["nav-link"]} label="책 목록" />
          <Tab className={classes["nav-link"]} label="리뷰 작성" />
          <Tab className={classes["nav-link"]} label="찜목록" />
        </Tabs>
      </Box>
      {value === 0 && <PurchaseBook books={bookList} />}
      {value === 1 && <MyPageReview reviews={reviewList} />}
      {value === 2 && <LikeList likes={likeList} />}
    </Box>
  );
}
