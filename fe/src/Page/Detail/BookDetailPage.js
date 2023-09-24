import BookDetailBanner from "../../Component/ProductDetail/BookDetailBanner.js";

import classes from './BookDetailPage.module.css';

export default function BookDetailPage() {
    const dummyBook = {
        title: "어린왕자",
        author: "생텍쥐페리",
        image: "./logo192.png",
        numPages: 276,
        numLikes: 374,
        updatedDate: "23.09.10",
        price: 900
    };
    return (
        <>
            <div className={classes['banner-wrapper']}>
                <BookDetailBanner book={dummyBook} />
            </div>
            <div className={classes.divider}></div>
        </>
    )
}