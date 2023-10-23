import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import { ClassNames } from '@emotion/react';

import classes from './PurchaseBook.module.css';

export default function PurchaseBook(){
    const dummyImagePath = [
        "./logo192.png","./logo192.png","./logo192.png","./logo192.png","./logo192.png",
        "./logo192.png","./logo192.png","./logo192.png","./logo192.png","./logo192.png",
        "./logo192.png",
    ]
    const purchasedBooks = dummyImagePath.map((book, idx)=>{
        return <Col xs = "auto"><img src = {book} className = {classes.image}/></Col>
    })
    return(
        <Container>
            <Row>
                {purchasedBooks}
            </Row>
        </Container>
    )
}