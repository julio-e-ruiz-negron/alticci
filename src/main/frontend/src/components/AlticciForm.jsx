import React, { useRef, useState, useLayoutEffect } from 'react';
import {
  faCheck,
  faTimes,
  faInfoCircle,
  faTriangleExclamation,
  faSearch,
  faExclamationTriangle
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import httpClient from '../api/httpClient';

const INDEX_REGEX =
  /^([0-9]|([1-9][0-9])|([1-9][0-9][0-9])|1[0-9][0-9][0-9]|2[0-4][0-9][0-9]|2500)$/;

const AlticciForm = () => {
  const indexRef = useRef();
  const errRef = useRef();
  const successRef = useRef();

  const [index, setIndex] = useState('');
  const [validIndex, setValidIndex] = useState(false);
  const [indexFocus, setIndexFocus] = useState(false);

  const [errMsg, setErrMsg] = useState('');
  const [successMsg, setSuccessMsg] = useState('');

  useLayoutEffect(() => {
    indexRef.current.focus();
  }, []);

  useLayoutEffect(() => {
    setValidIndex(INDEX_REGEX.test(index));
  }, [index]);

  useLayoutEffect(() => {
    setErrMsg('');
    setSuccessMsg('');
  }, [index]);

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!INDEX_REGEX.test(index)) {
      setErrMsg('Invalid Entry');
      return;
    }
    httpClient
      .get(index)
      .then(({ data }) => {
        setSuccessMsg('Sequence Number: ' + data);
        successRef.current.focus();
      })
      .catch(({ data, status }) => {
        if (status === 400) {
          console.log(data);
          console.log(status);
          setErrMsg('Invalid response retured from server');
        }
        errRef.current.focus();
      });
  };

  return (
    <section className="flex flex-col justify-start p-4 mt-4 bg-[#f6f8fa] border-t border-r border-b border-l border-solid border-gray-300 rounded-md">
      <form onSubmit={handleSubmit}>
        <label htmlFor="index" className="pr-1.5 text-sm">
          Enter Index Number:
          <FontAwesomeIcon
            icon={faTimes}
            size="sm"
            className={`${
              index && !validIndex ? 'text-red-600 w-7' : 'hidden'
            }`}
          />
          <FontAwesomeIcon
            icon={faCheck}
            size="sm"
            className={`${validIndex ? 'text-lime-500 w-7' : 'hidden'}`}
          />
        </label>
        <div className="flex items-center relative">
          <input
            className="flex-auto focus:outline-0 border-t border-r border-b border-l border-solid border-gray-300 p-1 pr-7 text-black text-md"
            type="text"
            id="index"
            ref={indexRef}
            autoComplete="off"
            onChange={(e) => setIndex(e.target.value)}
            required
            aria-invalid={!validIndex && index ? 'true' : 'false'}
            aria-describedby="uidindex"
            onFocus={() => setIndexFocus(true)}
            onBlur={() => setIndexFocus(false)}
          />
          <button
            title="Find Alticci Sequence"
            className="absolute right-1 top-1/2 -translate-y-1/2 pl-1 pr-1 text-center text-white bg-green-600 disabled:bg-gray-300 disabled:text-gray-500 disabled:cursor-no-drop"
            disabled={validIndex ? false : true}
          >
            <FontAwesomeIcon icon={faSearch} size="sm" />
          </button>
        </div>
        <div className="mt-2.5">
          <p
            id="uidindex"
            className={`${
              indexFocus && index && !validIndex
                ? 'p-1 [&>svg]:mr-2 rounded-md bg-black text-xs text-white'
                : 'hidden'
            }`}
          >
            <FontAwesomeIcon icon={faInfoCircle} />
            Please enter a number from 0 to 2500
          </p>
        </div>
        <p
          ref={successRef}
          className={`${successMsg ? 'text-md color: white' : 'hidden'}`}
        >
          {successMsg}
        </p>
        <p
          ref={errRef}
          className={`${
            errMsg
              ? 'p-1 [&>svg]:mr-2 rounded-md bg-red-600  text-xs text-white'
              : 'hidden'
          }`}
          aria-live="assertive"
        >
          <FontAwesomeIcon icon={faExclamationTriangle} />
          {errMsg}
        </p>
      </form>
    </section>
  );
};

export default AlticciForm;
