import {useState, useEffect} from 'react';

import Carousel from 'react-bootstrap/Carousel';
import BannerCard from "../../Component/Card/BannerCard";
import BookPreview from '../../Component/Preview/BookPreview';

import axios from 'axios';

import classes from './BookShopPage.module.css';

// #######################  API로 해당하는 리스트 가져오기 ##########################
const getBestEbookListAPI = "http://localhost:8080/ebook/list?page=0";
const getOnSaleEbookListAPI = "http://localhost:8080/ebook/list?page=0";
const getUpdateEbbokListAPI = "http://localhost:8080/ebook/list?page=0";

export default function () {
    const [bestSellerBook, setBestSellerBook] = useState([{
        id: "default",
        image: "default",
        name: "default",
        author: "default",
    }]);
    const [onSalebook, setOnSaleBook] = useState([{
        id: "default",
        image: "default",
        name: "default",
        author: "default",
    }]);
    const [updatedBook, setUpdatedBook] = useState([{
        id: "default",
        image: "default",
        name: "default",
        author: "default",
    }]);
    const dummyInfo = [
        { title: "이달의 책", subtitle: "이달의 가장 인기있는 책을 만나보세요", images: bestSellerBook },
        { title: "할인 중인 책", subtitle: "저렴한 가격에 책을 구매할 수 있는 기회", images: onSalebook },
        { title: "업데이트 된 책", subtitle: "새로 업데이트 된 책들의 오디오북을 들어보세요", images: updatedBook }
    ];
    // API를 통해서 데이터 가져오기
    useEffect(() => {
        axios.all([axios.get(getBestEbookListAPI), axios.get(getOnSaleEbookListAPI), axios.get(getUpdateEbbokListAPI)])
            .then(axios.spread((res1, res2,res3) => {
                const resData1 = (res1.data.content).map((book)=>({
                    id: book.id,
                    image: book.imageUrl,
                    name: book.ebookName,
                    author: book.author
                }));
                setBestSellerBook(resData1);
                const resData2 = (res2.data.content).map((book)=>({
                    id: book.id,
                    image: book.imageUrl,
                    name: book.ebookName,
                    author: book.author
                }));
                setOnSaleBook(resData2);
                const resData3 = (res3.data.content).map((book)=>({
                    id: book.id,
                    image: book.imageUrl,
                    name: book.ebookName,
                    author: book.author
                }));
                setUpdatedBook(resData3);
            })).catch((err)=> console.log(err));
    }, []);
    const previewList = dummyInfo.map((preview) => {
        return (
            <div className={classes['book-preview']} key={preview.title}>
                <BookPreview
                    title={preview.title}
                    subtitle={preview.subtitle}
                    images={preview.images}
                    link={"./"}
                />
            </div>

        );
    });
    return (
        <>
            <div className={classes['banner-wrapper']}>
                <Carousel>
                    <Carousel.Item>
                        <BannerCard imagePath="./logo192.png" title="어린왕자" description="생텍쥐페리 작가의 희대의 명작!" price="900원" salesDescription="(~9월 24일 까지)" />
                    </Carousel.Item>
                    <Carousel.Item>
                        <BannerCard imagePath="./logo192.png" title="어린왕자" description="생텍쥐페리 작가의 희대의 명작!" price="900원" salesDescription="(~9월 24일 까지)" />
                    </Carousel.Item>
                </Carousel>
            </div>
            {/* 리스트를 동적으로 가져옴 */}
            {previewList}
        </>
    );
};