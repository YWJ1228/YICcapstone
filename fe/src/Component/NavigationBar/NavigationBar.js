import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import Button from 'react-bootstrap/Button';

import classes from './NavigationBar.module.css';

function NavigationBar(props) {
    return (
        <div className = {classes.wrapper}>
            <Navbar className="bg-body-tertiary" >
                <Container>
                    <Navbar.Brand href="/" className={classes.brand}>
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
                <Nav>
                    <Nav.Item>
                        <Nav.Link href="/bookShop" className={classes.menu}>책</Nav.Link>
                    </Nav.Item>
                    <Nav.Item>
                        <Nav.Link href="/voiceShop" className={classes.menu}>음성모델</Nav.Link>
                    </Nav.Item>
                    <Nav.Item>
                        <Nav.Link href="/mypage" className={classes.menu}>내 서재</Nav.Link>
                    </Nav.Item>
                </Nav>
                {!props.isLogged && <Button href="/login" className={classes['login-button']}><div className={classes.menu}>로그인</div></Button>}
                {props.isLogged && <Button href="/" className={classes['login-button']}><div className={classes.menu}>내정보</div></Button>}

            </Navbar>
            <div className={classes.divider} />
        </div>


    );




}
export default NavigationBar;