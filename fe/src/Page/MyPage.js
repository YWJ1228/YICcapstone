import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

export default function MyPage() {
    return (
        <>
            <div>내 서재</div>
            <Container>
                <Row>
                    <Col><a href="/changeinfo" >개인정보수정</a></Col>
                </Row>
            </Container>
        </>
    )
};