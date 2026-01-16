import { Link } from 'react-router-dom';

export function HomePage() {
  return (
    <section className="space-y-6">
      <h1 className="text-3xl font-semibold">Franchise Growth Platform</h1>
      <p className="text-slate-700 max-w-xl">
        Connect franchise seekers, shop owners, landowners, and brands with the right opportunities,
        without overwhelming anyone.
      </p>
      <div className="grid gap-4 sm:grid-cols-3">
        <CtaCard title="List Your Shop" description="Turn your existing shop into a franchise-ready profile." />
        <CtaCard title="Find Franchise" description="Discover brands that fit your budget and location." />
        <CtaCard title="Grow Your Brand" description="Scale your brand with structured franchise models." />
      </div>
    </section>
  );
}

function CtaCard({ title, description }) {
  return (
    <Link
      to="/signup"
      className="block rounded-lg border bg-white p-4 hover:border-slate-400 transition"
    >
      <h2 className="font-medium mb-1">{title}</h2>
      <p className="text-sm text-slate-600">{description}</p>
    </Link>
  );
}

