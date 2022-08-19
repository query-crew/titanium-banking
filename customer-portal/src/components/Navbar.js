
import './Navbar.css';

const Navbar = () => {


    return(
        <>
        <nav className="app-navbar navbar navbar-expand-sm navbar-light ">
            <div className="container-fluid">
                {/* <a className="navbar-brand" href="#">Titanium Banking</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
                </button> */}
                <div className="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav">
                    <li className="nav-item">
                    <a className="nav-link" aria-current="page" href="#">Contacts</a>
                    </li>
                    <li className="nav-item">
                    <a className="nav-link" href="#">ATMs/Locations</a>
                    </li>
                    <li className="nav-item">
                    <a className="nav-link" href="#">Credit Cards</a>
                    </li>
                    <li className="nav-item">
                    <a className="nav-link" href="#" >Loans</a>
                    </li>
                    <li className="nav-item">
                    <a className="nav-link" href="#" >Live Chat</a>
                    </li>
                </ul>
                </div>
            </div>
        </nav>
        </>
    );
}

export default Navbar;