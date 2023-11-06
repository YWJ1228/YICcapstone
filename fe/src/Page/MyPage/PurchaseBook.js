
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';


import classes from './PurchaseBook.module.css';

export default function PurchaseBook(props){
    const purchasedBooks = (props.books).map((book, idx)=>{
        return <Col xs = "auto" key = {idx}><img src = {book} className = {classes.image}/></Col>
    })
    return(
        <Container>
            <Row>
                {purchasedBooks}
            </Row>
        </Container>
    )
}