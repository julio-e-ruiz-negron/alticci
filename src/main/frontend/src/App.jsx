import React from 'react';
import AlticciForm from './components/AlticciForm';
import logo from './assets/altice_logo.png';

const App = () => {
  return (
    <>
      <header className="w-full pt-8 pb-6 flex justify-center items-center">
        <img src={logo} alt="Altice Logo" />
      </header>
      <main>
        <div className="w-80 my-0 mx-auto pl-4 pr-4">
          <div className="text-2xl font-semibold">
            <h1 className="text-center font-thin">Find Alticci Sequence</h1>
          </div>
          <AlticciForm />
        </div>
      </main>
    </>
  );
};

export default App;
