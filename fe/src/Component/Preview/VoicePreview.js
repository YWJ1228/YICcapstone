import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './VoicePreview.module.css';

/**
 * props로 받는 properties
 * 
 * 1. images : 이미지 경로
 * 2. names : 연예인 이름
 * 3. jobs : 연예인 직업
 * 4. descriptions : 연예인 소속
 * 5. link : 더보기 링크 경로
 */

export default function VoicePreview(props) {
    const voiceArr = (props.voices).map((voice) => {
        return (
            <Col key={voice.id}>
                <Row><img src={voice.image} className={classes.image} /></Row>
                <Row className={classes.name}><div>{voice.name}</div></Row>
                <Row className={classes.job}><div>{voice.job}</div></Row>
                <Row className={classes.description}><div>{voice.description}</div></Row>
            </Col>
        );
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
                {voiceArr}
            </Row>
        </Container>
    );
};