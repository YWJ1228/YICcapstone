import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import classes from './VoicePreview.module.css';


export default function VoicePreview(props) {
    const voiceArr = (props.voices).map((voice) => {
        return (
            <Col key={voice.id}>
                <a href={"/voiceDetail/" + voice.id}>
                    <Row><img src={voice.image} className={classes.image} /></Row>
                </a>
                <Row className={classes.name}><div>{voice.name}</div></Row>
                <Row className={classes.job}><div>{voice.job}</div></Row>
                <Row className={classes.description}><div>{voice.description}</div></Row>
            </Col>
        );
    });
    return (
        <Container>
            <Row className={classes.title}>{props.title}</Row>
            <Row >
                {voiceArr}
            </Row>
        </Container>
    );
};