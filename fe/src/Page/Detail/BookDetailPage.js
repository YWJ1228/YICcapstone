import {useState} from 'react';

import DetailBanner from "../../Component/ProductDetail/DetailBanner.js.js";
import DetailDescription from "../../Component/ProductDetail/DetailDescription.js";
import DetailReviews from "../../Component/ProductDetail/DetailReviews.js";

import classes from './BookDetailPage.module.css';

import axios from 'axios'

export default function BookDetailPage(props) {
    const dummyReviews = [
        { id: "username1", uploadDate: "23.08.10", text: "어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다." },
        { id: "usname22", uploadDate: "23.08.10", text: "어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다." },
        { id: "usernme1333", uploadDate: "23.08.10", text: "어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다." },
        { id: "usernme144", uploadDate: "23.08.10", text: "어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다." },
    ];
    const [bookInfo, setBookInfo] = useState({
        title: "어린왕자",
        author: "생텍쥐페리",
        image: "./logo192.png",
        numPages: 276,
        numLikes: 374,
        updatedDate: "23.09.10",
        price: 900,
        description: " 보아뱀이 코끼리 먹는 이야기입니다.",
        reviews: dummyReviews
    });
    axios.get("http://localhost:8080/api/ebook/" + props.ebookId)
        .then(function (response) {
            setBookInfo({
                ebookName: response.data.ebookName,
                author: response.data.author,
                price: response.data.price,
                content: response.data.description,
                comment: response.data.comment,
                imageUrl: response.data.imageUrl
            });
        }).catch(function (error) {
            console.log(error);
            console.log("Book Detail loading error");
        });


    return (
        <>
            <div className={classes['banner-wrapper']}>
                <DetailBanner book={bookInfo} type="book" />
            </div>
            <div className={classes.divider}></div>
            <div className={classes['description-wrapper']}>
                <DetailDescription title="책 소개" description={bookInfo.description} />
            </div>
            <div className={classes['reviews-wrapper']}>
                <DetailReviews title="후기" reviews={bookInfo.reviews} />
            </div>
        </>
    )
}