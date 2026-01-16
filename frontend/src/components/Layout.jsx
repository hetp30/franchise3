import { Link } from 'react-router-dom';

export function Layout({ children }) {
  return (
    <div className="min-h-screen flex flex-col bg-slate-50">
      <header className="border-b bg-white">
        <div className="max-w-5xl mx-auto flex items-center justify-between px-4 py-3">
          <Link to="/" className="font-semibold text-lg">
            Franchise Platform
          </Link>
          <nav className="space-x-4 text-sm">
            <Link to="/signup">List Your Shop</Link>
            <Link to="/signup">Find Franchise</Link>
            <Link to="/signup">Grow Your Brand</Link>
          </nav>
        </div>
      </header>
      <main className="flex-1">
        <div className="max-w-3xl mx-auto px-4 py-8">{children}</div>
      </main>
    </div>
  );
}

