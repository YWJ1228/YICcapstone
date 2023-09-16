
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import Button from 'react-bootstrap/Button';

function NavigationBar(props) {
    return (
        <>
            <Navbar className="bg-body-tertiary">
                <Container>
                    <Navbar.Brand href="/">
                        <img
                            alt=""
                            src={props.img_src}
                            width="30"
                            height="30"
                            className="d-inline-block align-top"
                        />{' '}
                        최애의 보이스
                    </Navbar.Brand>
                </Container>
                <Nav className="justify-content-end" activeKey="/home">
                    <Nav.Item>
                        <Nav.Link href="/books">책</Nav.Link>
                    </Nav.Item>
                    <Nav.Item>
                        <Nav.Link href="/voiceshop">음성모델</Nav.Link>
                    </Nav.Item>
                    <Nav.Item>
                        <Nav.Link href="/mypage">내 서재</Nav.Link>
                    </Nav.Item>
                </Nav>
                <Button href="/login">로그인</Button>
            </Navbar>

        </>
    );




}
export default NavigationBar;