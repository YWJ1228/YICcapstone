import { useState, useRef } from 'react';

import Button from 'react-bootstrap/Button';

import Col from 'react-bootstrap/Col';
import ToggleButton from 'react-bootstrap/ToggleButton';
import ButtonGroup from 'react-bootstrap/ButtonGroup';

import classes from './SexBtn.module.css';

export default function SexBtn(props) {
    const [radioValue, setRadioValue] = useState('1');
    const radios = [
        { name: '남자', value: '1' },
        { name: '여자', value: '2' },
    ];
    const btnRef = useRef(null);
    const handlerClick = () =>{
        btnRef.current.style.setProperty('background-color','black');
    }

    const btn = radios.map((radio, idx) => (
        <ToggleButton
            key={idx}
            id={`radio-${idx}`}
            type="radio"
            className = {classes.btn}
            name="radio"
            value={radio.value}
            checked={radioValue === radio.value}
            onChange={(e) => setRadioValue(e.currentTarget.value)}
            inputRef = {handlerClick}
        >
            {radio.name}
        </ToggleButton>
    ));

    return (
        <ButtonGroup ref = {btnRef}>
            {btn}
        </ButtonGroup>
    );
}