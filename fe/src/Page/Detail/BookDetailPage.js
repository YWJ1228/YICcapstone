import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import { API } from "../../Config/APIConfig.js";
import NavigationBar from "../../Component/NavigationBar/NavigationBar.js";

import DetailBanner from "../../Component/ProductDetail/DetailBanner.js.js";
import DetailDescription from "../../Component/ProductDetail/DetailDescription.js";
import DetailReviews from "../../Component/ProductDetail/DetailReviews.js";

import classes from "./BookDetailPage.module.css";

import axios from "axios";
import { DebuggingMode } from "../../Config/Config.js";

export default function BookDetailPage() {
  const { ebookID } = useParams();
  const [reviews, setReview] = useState([]);
  const [reviewPages, setReviewPages] = useState(1);
  const [curReviewPage, setCurReviewPage] = useState(0);
  const [bookInfo, setBookInfo] = useState({
    title: "default",
    author: "default",
    image: "default",
    numPages: 0,
    numLikes: 0,
    updatedDate: "default",
    price: 0,
    description: "default",
  });
  useEffect(() => {
    axios
      .all([axios.get(`${API.LOAD_EBOOK}/${ebookID}`), axios.get(`${API.LOAD_REVIEW_EBOOKS}${ebookID}&page=${curReviewPage}`)])
      .then(
        axios.spread((res1, res2) => {
          console.log(res2);
          setReview(res2.data.content);
          setReviewPages(res2.data.totalPages);
          setBookInfo({
            id: res1.data.id,
            title: res1.data.ebookName,
            author: res1.data.author,
            image: res1.data.imageUrl,
            numPages: res1.data.pages,
            numLikes: res1.data.rating,
            updatedDate: res1.data.uploadedAt,
            price: res1.data.price,
            description: res1.data.comment,
          });

          DebuggingMode(["책 정보"], [res1.data]);
        })
      )
      .catch(function (error) {
        console.log(error);
        console.log("Book Detail loading error");
      });
  }, []);
  console.log(reviews)
  return (
    <>
      <NavigationBar img_src="logo.png" />
      <div style={{ width: "100%", height: "8rem" }} />
      <div className={classes["banner-wrapper"]}>
        <DetailBanner book={bookInfo} type="book" prdId = {ebookID}/>
      </div>
      <div className={classes.divider}></div>
      <div className={classes["description-wrapper"]}>
        <DetailDescription title="책 소개" description={bookInfo.description} />
      </div>
      <div className={classes["reviews-wrapper"]}>
        <DetailReviews title="후기" type = "book" reviews={reviews} totalPages = {reviewPages} curReviewPage = {curReviewPage} setCurPage = {setCurReviewPage} id = {ebookID}/>
      </div>
    </>
  );
}
