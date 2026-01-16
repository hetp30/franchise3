import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { updateRoles } from '../services/apiClient';

const ROLE_OPTIONS = [
  {
    key: 'ROLE_SEEKER_SHOP',
    label: 'I already run a shop/business',
    description: 'You have an existing shop and want franchise opportunities.',
    icon: '🏪'
  },
  {
    key: 'ROLE_SEEKER_INDIVIDUAL',
    label: 'I don’t have a shop yet (Individual)',
    description: 'You are exploring franchises as an individual investor.',
    icon: '🧍'
  },
  {
    key: 'ROLE_LANDOWNER',
    label: 'I own land/property',
    description: 'You want to lease your land to the right franchise.',
    icon: '🏞️'
  },
  {
    key: 'ROLE_GIVER_LOCAL_BRAND',
    label: 'I want to franchise my own brand',
    description: 'You run a local brand and want to scale through franchising.',
    icon: '🚀'
  }
];

export function RoleSelectionPage() {
  const navigate = useNavigate();
  const [selected, setSelected] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!selected) {
      setError('Please select the option that best matches your current situation.');
      return;
    }
    const userId = localStorage.getItem('userId');
    if (!userId) {
      setError('We could not find your account. Please signup again.');
      return;
    }
    setLoading(true);
    setError('');
    try {
      await updateRoles(userId, [selected]);
      // Redirect shop owners to profile page, others to dashboard
      if (selected === 'ROLE_SEEKER_SHOP') {
        navigate('/shop-profile');
      } else {
        navigate('/dashboard');
      }
    } catch (err) {
      setError('Could not save your choice. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <section className="space-y-4">
      <h1 className="text-2xl font-semibold">Tell us about yourself</h1>
      <p className="text-sm text-slate-700 max-w-xl">
        We ask about your current situation (not your long-term ambition) so that we can show the
        most relevant next steps and avoid unnecessary questions.
      </p>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div className="grid gap-3 sm:grid-cols-2">
          {ROLE_OPTIONS.map((role) => (
            <button
              key={role.key}
              type="button"
              onClick={() => setSelected(role.key)}
              className={`text-left rounded-lg border bg-white p-4 text-sm transition ${
                selected === role.key ? 'border-blue-600 shadow-sm' : 'border-slate-200'
              }`}
            >
              <div className="flex items-center gap-2 mb-1">
                <span className="text-lg">{role.icon}</span>
                <span className="font-medium">{role.label}</span>
              </div>
              <p className="text-slate-600 text-xs">{role.description}</p>
            </button>
          ))}
        </div>
        {error && <p className="text-sm text-red-600">{error}</p>}
        <button
          type="submit"
          disabled={loading}
          className="inline-flex items-center rounded-md bg-blue-600 px-4 py-2 text-white text-sm disabled:opacity-70"
        >
          {loading ? 'Saving...' : 'Continue'}
        </button>
      </form>
    </section>
  );
}

