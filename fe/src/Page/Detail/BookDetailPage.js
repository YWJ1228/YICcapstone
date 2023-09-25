import DetailBanner from "../../Component/ProductDetail/DetailBanner.js.js";
import DetailDescription from "../../Component/ProductDetail/DetailDescription.js";
import DetailReviews from "../../Component/ProductDetail/DetailReviews.js";

import classes from './BookDetailPage.module.css';

export default function BookDetailPage() {
    const dummyReviews = [
        { id: "username1", uploadDate: "23.08.10", text: "어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다." },
        { id: "usname22", uploadDate: "23.08.10", text: "어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다." },
        { id: "usernme1333", uploadDate: "23.08.10", text: "어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다." },
        { id: "usernme144", uploadDate: "23.08.10", text: "어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다.어린왕자 후기 1번입니다." },
    ];
    const dummyBook = {
        title: "어린왕자",
        author: "생텍쥐페리",
        image: "./logo192.png",
        numPages: 276,
        numLikes: 374,
        updatedDate: "23.09.10",
        price: 900,
        description: " 보아뱀이 코끼리 먹는 이야기입니다.\
        보아뱀이 코끼리 먹는 이야기입니다.\
        보아뱀이 코끼리 먹는 이야기입니다.\
        보아뱀이 코끼리 먹는 이야기입니다.\
        보아뱀이 코끼리 먹는 이야기입니다.",
        reviews: dummyReviews
    };

    return (
        <>
            <div className={classes['banner-wrapper']}>
                <DetailBanner book={dummyBook} type = "book"/>
            </div>
            <div className={classes.divider}></div>
            <div className={classes['description-wrapper']}>
                <DetailDescription title="책 소개" description={dummyBook.description} />
            </div>
            <div className={classes['reviews-wrapper']}>
                <DetailReviews title="후기" reviews={dummyBook.reviews} />
            </div>
        </>
    )
}