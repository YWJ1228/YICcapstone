import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';


import classes from './DetailDescription.module.css';

export default function DetailDescription(props) {
    return (
        <Container>
            <Row>
                <div className={classes.title}>{props.title}</div>
            </Row>
            <Row>
                <div className={classes.description}>{props.description}</div>
            </Row>
        </Container>

    );
}