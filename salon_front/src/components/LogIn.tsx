import React, { useState } from 'react';
import type { FormEvent, ChangeEvent } from 'react';

interface FormData {
  login: string;
  password: string;
}

const LoginForm: React.FC = () => {
  const [formData, setFormData] = useState<FormData>({
    login: '',
    password: '',
  });

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    // Здесь можно добавить логику авторизации, например, API-запрос
    console.log('Данные для авторизации:', formData);
    // Очистка формы после отправки (опционально)
    setFormData({ login: '', password: '' });
  };

  return (
    <form onSubmit={handleSubmit} className='container'>
      <div className='content'>
        <div className='login_title'>
          <h1>Авторизация</h1>
          <p>Введите данные для авторизации!</p>
        </div>
        <div className='login_div'>
          {' '}
          <label htmlFor='login'>Логин:</label>
          <input
            type='text'
            id='login'
            name='login'
            value={formData.login}
            onChange={handleChange}
            placeholder='Введите логин'
            required
          />
        </div>
      </div>
      <div className='login_div'>
        <label htmlFor='password'>Пароль:</label>
        <input
          type='password'
          id='password'
          name='password'
          value={formData.password}
          onChange={handleChange}
          placeholder='Введите пароль'
          required
        />
      </div>
      <button type='submit'>Войти</button>
    </form>
  );
};

export default LoginForm;
