import { useEffect, useState } from 'react';
import { API } from '../../Config/APIConfig';
import { PageConfig } from '../../Config/PageConfig';

import axios from 'axios';

import Stack from 'react-bootstrap/Stack';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';

import classes from './BookPreviewAll.module.css';

export default function BookPreviewAll(props) {
    const [currentCategory, setCurrentCategory] = useState('all');
    const [currentPage, setCurrentPage] = useState(1);
    const [pageCnt, setPageCnt] = useState(1);
    const [entireBook, setEntireBook] = useState([{
        id: "default",
        image: "default",
        name: "default",
        author: "default"
    }]);
    useEffect(() => {
        var api = `${API.LOAD_CATEGORY_EBOOKS}${currentCategory}&page=${currentPage - 1}&size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}`;
        if (currentCategory === 'all') {
            api = `${API.LOAD_ALL_EBOOKS}${currentPage - 1}&size=${PageConfig.EBOOK_PRODUCT_PER_PAGE}`;
        }
        axios.get(api)
            .then((response) => {
                const resData = (response.data.content).map((book) => ({
                    id: book.id,
                    image: book.imageUrl,
                    name: book.ebookName,
                    author: book.author
                }));
                setEntireBook(resData);
            }).catch((err) => console.log(err));
        axios.get(`${API.NUM_PAGES_EBOOKLIST}${PageConfig.EBOOK_PRODUCT_PER_PAGE}`)
            .then((res) => {
                setPageCnt(res.data);
            }).catch((err) => { console.log(err) });

    }, [currentPage, currentCategory]);
    function CategoryClickHandler(category) {
        setCurrentCategory(category);
        setCurrentPage(1);
    }
    // 카테고리 리스트 시각화
    const catergoryArr = (PageConfig.EBOOK_CATEGORY_LIST).map((category) => {
        return (
            <Button
                className={currentCategory !== category ? classes.category : classes['category-focus']}
                onClick={() => { CategoryClickHandler(category) }}>
                {category}
            </Button>
        );
    });
    // 각 상품 카드에 대한 코드
    const imageArr = (entireBook).map((img) => {
        return (
            <Col key={img.id}>
                <a href={props.link}>
                    <Row><img src={img.image} className={classes.image} /></Row>
                </a>
                <Row className={classes.name}><div>{img.name}</div></Row>
                <Row className={classes.author}><div>{img.author}</div></Row>
            </Col>);
    });
    // 페이지 버튼 배열
    const buttonArr = (Array.from({ length: pageCnt }, (v, i) => i + 1)).map((pageNum) => {
        return (
            <Button
                className={pageNum === currentPage ? classes['page-button-focus'] : classes['page-button']}
                onClick={() => { setCurrentPage(pageNum) }}>
                {pageNum}
            </Button>);
    });

    return (
        <Container>
            <Row className={classes.title}>{props.title}</Row>
            <Row className={classes['category-wrapper']}><Stack direction="horizontal" gap={3}>{catergoryArr}</Stack></Row>
            <hr></hr>
            <Row>{imageArr}</Row>
            <Row><Col className={classes['button-wrapper']}><ButtonGroup>{buttonArr}</ButtonGroup></Col></Row>

        </Container>

    );
};