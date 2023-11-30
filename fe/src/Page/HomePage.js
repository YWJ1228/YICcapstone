import { useState, useEffect } from "react";
import { API } from "../Config/APIConfig";
import { PageConfig, DebuggingMode } from "../Config/Config";

import axios from "axios";


import NavigationBar from "../Component/NavigationBar/NavigationBar";
import Stack from "react-bootstrap/Stack";
import BookPreview from "../Component/Preview/BookPreview";
import VoicePreview from "../Component/Preview/VoicePreview";
import Footer from "../Component/Footer/Footer";

import classes from "./HomePage.module.css";
import { getCookies } from "../Component/Cookies/LoginCookie";

import BannerCarousel from "../Component/Carousel/BannerCarousel";

export default function HomePage() {
  const [bestSellerBook, setBestSellerBook] = useState([PageConfig.EBOOK_PAGE_DEFAULT_STATE]);
  const [bestSellerVoice, setBestSellerVoice] = useState([PageConfig.VOICE_PAGE_DEFAULT_STATE]);

  useEffect(() => {
    axios
      .all([
        axios.get(`${API.LOAD_POPULAR_EBOOKS}`, {
          headers: { Authorization: `${getCookies("accessToken")}` },
        }),
        axios.get(`${API.LOAD_POPULAR_VOICES}`),
        {
          headers: { Authorization: `${getCookies("accessToken")}` },
        },
      ])
      .then(
        axios.spread((res1, res2) => {
          const resData1 = res1.data.content.map((book) => ({
            id: book.id,
            image: book.imageUrl,
            name: book.ebookName,
            author: book.author,
            price: book.price,
          }));
          setBestSellerBook(resData1);
          const resData2 = res2.data.content.map((voice) => ({
            id: voice.id,
            image: voice.imageUrl,
            name: voice.celebrityName,
            category: voice.category,
            description: voice.comment,
            price: voice.price,
          }));
          setBestSellerVoice(resData2);
          // 디버깅
          DebuggingMode(["배너", "이 달의 책", "이 달의 TTS"], [null, resData1, resData2]);
        })
      )
      .catch((err) => console.log(err));
  }, []);
  return (
    <>
      <NavigationBar showMenuBar = {true}/>
      <div style={{ width: "100%", height: "8rem" }} />
      <Stack>
      <div className={classes.carousel} >
        <BannerCarousel type='home' />
      </div>
      <div className={classes["book-preview"]}>
        <BookPreview {...PageConfig.HOMEPAGE_TITLES[0]} images={bestSellerBook} />
      </div>
      <div className={classes["book-preview"]}>
        <VoicePreview {...PageConfig.HOMEPAGE_TITLES[1]} voices={bestSellerVoice} />
      </div>
      </Stack>
      <Footer />
    </>
  );
}
