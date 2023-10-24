import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

import { getCookies } from '../../Component/Cookies/LoginCookie.js';

import { API } from '../../Config/APIConfig.js';

import DetailBanner from "../../Component/ProductDetail/DetailBanner.js.js";
import DetailDescription from "../../Component/ProductDetail/DetailDescription.js";
import DetailReviews from "../../Component/ProductDetail/DetailReviews.js";

import classes from './BookDetailPage.module.css';

import axios from 'axios'
import { DebuggingMode } from '../../Config/Config.js';


export default function BookDetailPage() {
    const { ebookID } = useParams();
    const dummyReviews = [
        { id: 1, nickname: "username1", uploadDate: "23.08.10", text: "어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다." },
        { id: 2, nickname: "usname22", uploadDate: "23.08.10", text: "어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다." },
        { id: 3, nickname: "usernme1333", uploadDate: "23.08.10", text: "어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다." },
        { id: 4, nickname: "usernme144", uploadDate: "23.08.10", text: "어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다." },
    ];
    const [bookInfo, setBookInfo] = useState({
        title: "default",
        author: "default",
        image: "default",
        numPages: 0,
        numLikes: 0,
        updatedDate: "default",
        price: 0,
        description: "default",
        reviews: dummyReviews
    });

    useEffect(() => {
        axios.get(`${API.LOAD_EBOOK}/${ebookID}`)
            .then(function (response) {
                setBookInfo({
                    id : response.data.id,
                    title: response.data.ebookName,
                    author: response.data.author,
                    image: response.data.imageUrl,
                    numPages: response.data.pages,
                    numLikes: response.data.rating,
                    updatedDate: response.data.uploadedAt,
                    price: response.data.price,
                    description: response.data.content,
                    reviews: dummyReviews
                });

                DebuggingMode(["책 정보"], [response.data]);
            }).catch(function (error) {
                console.log(error);
                console.log("Book Detail loading error");
            });
    }, []);


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