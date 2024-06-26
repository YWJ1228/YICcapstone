import { useState, useEffect } from "react";
import { API } from "../../Config/APIConfig";
import { DebuggingMode, PageConfig } from "../../Config/Config";

import axios from "axios";
import NavigationBar from "../../Component/NavigationBar/NavigationBar";
import BookPreview from "../../Component/Preview/BookPreview";
import classes from "./BookShopPage.module.css";
import BookPreviewAll from "../../Component/Preview/BookPreviewAll";
import BannerCarousel from "../../Component/Carousel/BannerCarousel";
import { Stack } from "react-bootstrap";

export default function () {
  const [bannerBook, setBannerBook] = useState([
    PageConfig.EBOOK_PAGE_DEFAULT_STATE,
  ]);
  const [bestSellerBook, setBestSellerBook] = useState([
    PageConfig.EBOOK_PAGE_DEFAULT_STATE,
  ]);
  const [updatedBook, setUpdatedBook] = useState([
    PageConfig.EBOOK_PAGE_DEFAULT_STATE,
  ]);

  const stateArr = [bestSellerBook, updatedBook];

  useEffect(() => {
    axios
      .all([
        axios.get(API.LOAD_POPULAR_EBOOKS),
        axios.get(API.LOAD_RECENT_EBOOKS),
        axios.get(API.LOAD_BANNER_EBOOKS),
      ])
      .then(
        axios.spread((res1, res2, res3) => {
          const resData1 = res1.data.content.map((book) => ({
            id: book.id,
            image: book.imageUrl,
            name: book.ebookName,
            author: book.author,
            price: book.price,
          }));
          setBestSellerBook(resData1);
          const resData2 = res2.data.content.map((book) => ({
            id: book.id,
            image: book.imageUrl,
            name: book.ebookName,
            author: book.author,
            price: book.price,
          }));
          setUpdatedBook(resData2);
          const resData3 = res3.data.content.map((book) => ({
            id: book.id,
            image: book.imageUrl,
            name: book.ebookName,
            author: book.author,
          }));
          setBannerBook(resData3);
          DebuggingMode(
            ["이 달의 책", "업데이트 된 책", "배너"],
            [resData1, resData2, resData3]
          );
        })
      )
      .catch((err) => {
        // console.log(err)
      });
  }, []);

  const previewList = PageConfig.EBOOK_SHOP_TITLES.map((preview, idx) => {
    return (
      <div className={classes["book-preview"]} key={idx}>
        <BookPreview
          title={preview.title}
          subtitle={preview.subtitle}
          images={stateArr[idx]}
        />
      </div>
    );
  });
  return (
    <>
      <NavigationBar showMenuBar = {true} page = 'book'/>
      <div style={{ width: "100%", height: "8rem" }} />
      <Stack>
      <div className={classes.carousel} >
        <BannerCarousel type = 'book'/>
      </div>
      {previewList}
      <BookPreviewAll title="전체 페이지" subtitle="전체 목록" />
      </Stack>
    </>
  );
}
