import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './SearchPwd.module.css';
export default function SearchPwd(props) {
    return (
        <div >
            <Container className={classes.logo}><Row><Col >비밀번호 찾기</Col></Row></Container>
            <Card className={classes.card}>
                <Card.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="formEmail">
                            <Form.Label>아이디</Form.Label>
                            <Form.Control type="text" placeholder="HohyeongHoJe" />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formName">
                            <Form.Label>이름</Form.Label>
                            <Form.Control type="text" placeholder="홍길동" />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formEmail">
                            <Form.Label>이메일</Form.Label>
                            <Form.Control type="email" placeholder="HongilDong@example.com" />
                        </Form.Group>
                        <Button className={classes['login_btn']} variant="primary" type="submit">
                            비밀번호 찾기
                        </Button>
                    </Form>
                </Card.Body>
            </Card>
        </div>
    );
}