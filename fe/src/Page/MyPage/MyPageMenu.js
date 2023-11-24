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
import MyCart from "./MyCart";

import classes from "./MyPageMenu.module.css";

export default function MyPageMenu() {
  const [value, setValue] = useState(0);
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  const [renderItem, setRenderItem] = useState(0);
  const [bookList, setBookList] = useState([PageConfig.EBOOK_PAGE_DEFAULT_STATE]);
  const [voiceList, setVoiceList] = useState([]);
  const [puchasedEbookSize, setPurchasedEbookSize] = useState(0);
  const [reviewList, setReviewList] = useState([]);
  const [voiceReviewList, setVoiceReviewList] = useState([]);
  const [likeEbooks, setLikeEbooks] = useState([]);
  const [likeVoices, setLikeVoices] = useState([]);
  const [cartEbookList, setCartEbookList] = useState([]);
  const [cartVoiceList, setCartVoiceList] = useState([]);

  useEffect(() => {
    axios
      .all([
        axios.get(`${API.LOAD_PURCHASED_EBOOKS_SIZE}`, {
          headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
        }),
        axios.get(`${API.LOAD_PURCHASED_VOICES_SIZE}`,{
          headers : {Authorization : `Bearer ${getCookies('accessToken')}`},
        }),
        axios.get(`${API.LOAD_WISH_EBOOKS_SIZE}`, {
          headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
        }),
        axios.get(`${API.LOAD_WISH_VOICES_SIZE}`, {
          headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
        }),
        axios.get(`${API.LOAD_REVIEW_NOT_WRITTEN_EBOOKS_SIZE}`, {
          headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
        }),
        axios.get(`${API.LOAD_REVIEW_NOT_WRITTEN_VOICES_SIZE}`, {
          headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
        }),
      ])
      .then(
        axios.spread((res1,res2, res3, res4,res5, res6) => {
          setPurchasedEbookSize(res1.data.totalElements);
          axios
            .all([
              axios.get(`${API.LOAD_PURCHASED_EBOOKS}${res1.data.totalElements === 0 ? 1 : res1.data.totalElements}`, {
                headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
              }),
              axios.get(`${API.LOAD_REVIEW_NOT_WRITTEN_EBOOKS}${res5.data.totalElements}`, {
                headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
              }),
              axios.get(`${API.LOAD_REVIEW_NOT_WRITTEN_VOICES}${res6.data.totalElements}`,{
                headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
              }),
              axios.get(API.READ_EBOOK_CART, {
                headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
              }),
              axios.get(API.READ_VOICE_CART, {
                headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
              }),
              axios.get(`${API.LOAD_PURCHASED_VOICES}${res2.data.totalElements === 0 ? 1 : res2.data.totalElements}`,{
                headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
              }),
            ])
            .then(
              axios.spread((purchase1, review1,review2,cart1, cart2, purchase2) => {
                const resData1 = purchase1.data.content.map((book) => ({
                  id: book.purchaseId,
                  image: book.ebook.imageUrl,
                  name: book.ebook.ebookName,
                  author: book.ebook.author,
                  price: book.ebook.price,
                }));
                setBookList(resData1);
                const resData2 = review1.data.content.map((book) => ({
                  purchaseId: book.purchaseId,
                  id: book.ebook.id,
                  image: book.ebook.imageUrl,
                  name: book.ebook.ebookName,
                  author: book.ebook.author,
                  price: book.ebook.price,
                }));
                setReviewList(resData2);
                setVoiceReviewList(review2.data.content);
                setCartEbookList(cart1.data);
                setCartVoiceList(cart2.data);
                setVoiceList(purchase2.data.content)
              })
            )
          
            if(res3.data.totalElements !== 0){
              axios.get(`${API.LOAD_WISH_EBOOKS}${res3.data.totalElements}`, {
                headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
              }).then((wish)=>{setLikeEbooks(wish.data.content)
              })
            }
            if(res4.data.totalElements !== 0){
              axios.get(`${API.LOAD_WISH_VOICES}${res4.data.totalElements}`, {
                headers: { Authorization: `Bearer ${getCookies("accessToken")}` },
              }).then((wish)=>{setLikeVoices(wish.data.content)})
            }

        })
      )
  }, [renderItem]);

  return (
    <Box className={classes["tab-wrapper"]}>
      <Box sx={{ borderBottom: 1, borderColor: "divider" }} className={classes["nav-wrapper"]}>
        <Tabs value={value} aria-label="basic tabs example" onChange={handleChange}>
          <Tab className={classes["nav-link"]} label="구매 목록" />
          <Tab className={classes["nav-link"]} label="리뷰 작성" />
          <Tab className={classes["nav-link"]} label="찜 목록" />
          <Tab className={classes["nav-link"]} label="장바구니" />
        </Tabs>
      </Box>
      {value === 0 && <PurchaseBook books={bookList} voices={voiceList}/>}
      {value === 1 && <MyPageReview reviews={reviewList} voiceReview={voiceReviewList}/>}
      {value === 2 && <LikeList bookLike={likeEbooks} voiceLike={likeVoices} />}
      {value === 3 && <MyCart cartEbooks={cartEbookList} cartVoices={cartVoiceList} renderFunc={setRenderItem} renderVal={renderItem} />}
    </Box>
  );
}
