import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

import Grow from '@mui/material/Grow';

import classes from './VoicePreview.module.css';


export default function VoicePreview(props) {
    const voiceArr = (props.voices).map((voice, idx) => {
        return (
            <Col key={idx}>
                <Grow in={true} style={{ transformOrigin: 'center top' }}
                    {...(props.once ? { timeout: 0 } : { timeout: Math.abs((Math.floor(props.voices.length / 2)) - idx) * 1000 + 1000 })}>
                    <div>
                        <a href={"/voiceDetail/" + voice.id}>
                            <Row><img src={voice.image} className={classes.image} /></Row>
                        </a>
                        <Row className={classes.name}><div>{voice.name}</div></Row>
                        <Row className={classes.job}><div>{voice.job}</div></Row>
                        <Row className={classes.price}><div>{voice.price}원</div></Row>
                    </div>
                </Grow>
            </Col >
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