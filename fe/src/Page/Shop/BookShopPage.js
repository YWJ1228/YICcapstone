import Carousel from 'react-bootstrap/Carousel';


import BannerCard from "../../Component/Card/BannerCard";

import BookPreview from '../../Component/Preview/BookPreview';
import classes from './BookShopPage.module.css';
/**
 * API로 가져올 정보
 *  1. 이달의 책 리스트
 *  2. 할인 중인 책 리스트
 *  3. 업데이트 된 책 리스트
 * 
 *  각 책마다 가져올 정보
 *  1. id
 *  2. 이미지 경로
 *  3. 책 제목
 * 
 */
export default function () {
    const dummyBook = [
        { id: 1, image: "./logo192.png", name: "어린왕자" },
        { id: 2, image: "./logo192.png", name: "어린왕자" },
        { id: 3, image: "./logo192.png", name: "어린왕자" },
        { id: 4, image: "./logo192.png", name: "어린왕자" },
        { id: 5, image: "./logo192.png", name: "어린왕자" },
    ];
    const dummyInfo = [
        { title: "이달의 책", subtitle: "이달의 가장 인기있는 책을 만나보세요", images: dummyBook },
        { title: "할인 중인 책", subtitle: "저렴한 가격에 책을 구매할 수 있는 기회", images: dummyBook },
        { title: "업데이트 된 책", subtitle: "새로 업데이트 된 책들의 오디오북을 들어보세요", images: dummyBook }
    ];
    const previewList = dummyInfo.map((preview) => {
        return (
            <div className={classes['book-preview']} key = {preview.title}>
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