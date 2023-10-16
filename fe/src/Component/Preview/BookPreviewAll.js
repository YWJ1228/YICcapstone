import { useEffect, useState } from 'react';
import axios from 'axios';

import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';

import classes from './BookPreviewAll.module.css';

/**
    이달의 책
    이달의 가장 인기 있는 책을 만나보세요

 */
const getEntireEbookListAPI = "http://localhost:8080/ebook/list?page=";
const getTotalPage = [1, 2, 3, 4, 5, 6];

export default function BookPreviewAll(props) {
    const [currentPage, setCurrentPage] = useState(1);
    const [entireBook, setEntireBook] = useState([{
        id: "default",
        image: "default",
        name: "default",
        author: "default"
    }]);
    useEffect(() => {
        axios.get(getEntireEbookListAPI + (currentPage - 1))
            .then((response) => {
                const resData = (response.data.content).map((book) => ({
                    id: book.id,
                    image: book.imageUrl,
                    name: book.ebookName,
                    author: book.author
                }));
                setEntireBook(resData);
            }).catch((err) => console.log(err));
    }, [currentPage]);

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
    const buttonArr = getTotalPage.map((page, idx) => {
        return (
            <Button
            className={classes['page-button']}
                onClick={() => { setCurrentPage(idx + 1) }}>
                {idx + 1}
            </Button>);
    });

    return (
        <Container>
            <Row className={classes.title}>{props.title}</Row>
            <Row>
                <Col className={classes['sub-title']}>{props.subtitle}</Col>
                <Col xs={1} className={classes['more-wrapper']}>
                    <a href={props.link} className={classes.more}>&lt;더보기</a>
                </Col>
            </Row>
            <Row >
                {imageArr}
            </Row>

            <ButtonGroup>
                {buttonArr}
            </ButtonGroup>
        </Container>

    );
};