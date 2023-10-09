import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

export default function LikeList(){
    const dummyImagePath = [
        "./logo.png","./logo.png","./logo.png","./logo.png","./logo.png"
    ]
    const purchasedBooks = dummyImagePath.map((book, idx)=>{
        return <Col xs = "auto"><img src = {book}/></Col>
    })
    return(
        <Container>
            <Row>
                {purchasedBooks}
            </Row>
        </Container>
    )
}