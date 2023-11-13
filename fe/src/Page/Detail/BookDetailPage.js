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
  const [reviews, setReview] = useState([{
    userName : "default",
    content : "default",
    grade : 0,
    time : "default"
  }]);
  const [bookInfo, setBookInfo] = useState({
    title: "default",
    author: "default",
    image: "default",
    numPages: 0,
    numLikes: 0,
    updatedDate: "default",
    price: 0,
    description: "default",
    reviews: reviews,
  });
  console.log(`${API.REVIEW_EBOOK}${ebookID}&page=0`)
  useEffect(() => {
    axios
      .all([axios.get(`${API.LOAD_EBOOK}/${ebookID}`), axios.get(`http://localhost:8080/review/ebook?ebookId=1&page=0&size=5`)])
      .then(
        axios.spread((res1, res2) => {
          console.log(res2);
          res2.data.content.map((review)=>{
            setReview(review);
            
          })
          setBookInfo({
            id: res1.data.id,
            title: res1.data.ebookName,
            author: res1.data.author,
            image: res1.data.imageUrl,
            numPages: res1.data.pages,
            numLikes: res1.data.rating,
            updatedDate: res1.data.uploadedAt,
            price: res1.data.price,
            description: res1.data.content,
            reviews: reviews,
          });

          DebuggingMode(["책 정보"], [res1.data]);
        })
      )
      .catch(function (error) {
        console.log(error);
        console.log("Book Detail loading error");
      });
  }, []);

  return (
    <>
      <NavigationBar img_src="logo.png" />
      <div style={{ width: "100%", height: "8rem" }} />
      <div className={classes["banner-wrapper"]}>
        <DetailBanner book={bookInfo} type="book" />
      </div>
      <div className={classes.divider}></div>
      <div className={classes["description-wrapper"]}>
        <DetailDescription title="책 소개" description={bookInfo.description} />
      </div>
      <div className={classes["reviews-wrapper"]}>
        <DetailReviews title="후기" reviews={bookInfo.reviews} />
      </div>
    </>
  );
}
