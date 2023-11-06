import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './LikeList.module.css';

export default function LikeList(props){
    const purchasedBooks = (props.likes).map((book, idx)=>{
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