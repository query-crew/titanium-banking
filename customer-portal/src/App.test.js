import { render, screen } from '@testing-library/react';
import App from './App';
import SignInPage from './molecules/SignInPage'
import { store } from './store';
import { Provider } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';

test('renders the landing page', () => {
  render(<App />);
});

test('renders the signin page', () => {
  render(
    <Provider store={store}>
      <BrowserRouter>
        <SignInPage />
      </BrowserRouter>
    </Provider>
    )
  });

  test('remember me checkbox is checked on sign in card', () => {
    localStorage.setItem("checked", "true");
    render(
      <App/>
    )
    expect(screen.getByTestId("sign-in-card-checkbox")).toBeChecked();
    localStorage.removeItem("checked");
  })

  test('remember me checkbox is checked on sign page', () => {
    localStorage.setItem("checked", "true");
    render(
      <Provider store={store}>
        <BrowserRouter>
          <SignInPage />
        </BrowserRouter>
      </Provider>
    )
    expect(screen.getByTestId("expanded-sign-in-card-checkbox")).toBeChecked();
    expect(screen.getByTestId("expanded-sign-in-checkbox")).toBeChecked();
    localStorage.removeItem("checked");
  })


  test('remember me checkbox is not checked on sign in card', () => {
    localStorage.setItem("checked", "false");
    render(
      <App/>
    )
    expect(screen.getByTestId("sign-in-card-checkbox")).not.toBeChecked();
    localStorage.removeItem("checked");
  })

  test('remember me checkbox is not checked on sign page', () => {
    localStorage.setItem("checked", "false");
    render(
      <Provider store={store}>
        <BrowserRouter>
          <SignInPage />
        </BrowserRouter>
      </Provider>
    )
    expect(screen.getByTestId("expanded-sign-in-card-checkbox")).not.toBeChecked();
    expect(screen.getByTestId("expanded-sign-in-checkbox")).not.toBeChecked();
    localStorage.removeItem("checked");
  })

  test('username is present and remember me is checked', () => {
    localStorage.setItem("checked", "true");
    localStorage.setItem("username", "test");
    render(
      <App/>
    )
    expect(screen.getByTestId("sign-in-card-checkbox")).toBeChecked();
    expect(screen.getByLabelText("Username")).toHaveValue("test");
    localStorage.removeItem("checked");
    localStorage.removeItem("username");
  })

  test('username is not present and remember me is not checked', () => {
    render(
      <App/>
    )
    expect(screen.getByTestId("sign-in-card-checkbox")).not.toBeChecked();
    expect(screen.getByLabelText("Username")).toHaveValue("");
  });

  