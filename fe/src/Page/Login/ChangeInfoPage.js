import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';

import classes from'./ChangeInfoPage.module.css';
export default function ChangeInfoPage(){
    return(
        <div className={classes.wrapper}>
            <Form>
                <Form.Group className="mb-3" controlId="formGridName">
                    <Row className="mb-3">
                        <Form.Label>닉네임</Form.Label>
                        <Col lg = {10}>
                            <Form.Control type="text" />
                        </Col>
                        <Col>
                            <Button className = {classes['verify-button']}>
                                중복확인
                            </Button>
                        </Col>
                    </Row>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formGridCheckPassword">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control type="password" />
                    <Form.Label>비밀번호 확인</Form.Label>
                    <Form.Control />
                </Form.Group>

                

                <Button type="submit" className = {classes['submit']}>
                    수정 완료
                </Button>
            </Form>
            <Button type="button" className = {classes['submit']}>
                    회원 탈퇴
            </Button>
        </div>
    );
}