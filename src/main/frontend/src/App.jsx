import React from 'react';
import AlticciForm from './components/AlticciForm';
import logo from './assets/altice_logo.png';

const App = () => {
  return (
    <div className="h-screen w-screen flex flex-col justify-center items-center">
      <div className="w-72 sm:w-96 mx-auto pb-6 pt-6 border-0 rounded-none sm:rounded-md sm:border-2 sm:border-solid sm:border-slate-300">
        <header className="pb-6">
          <img src={logo} alt="Altice Logo" className="block mx-auto" />
        </header>
        <main className="pl-4 pr-4">
          <div className="text-xl sm:text-2xl font-semibold">
            <h1 className="text-center font-thin">Find Alticci Sequence</h1>
          </div>
          <AlticciForm />
        </main>
      </div>
    </div>
  );
};

export default App;
