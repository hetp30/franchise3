import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { getReadinessScore, calculateReadinessScore, getShopProfile } from '../services/apiClient';

export function DashboardPage() {
  const [score, setScore] = useState(null);
  const [shopProfile, setShopProfile] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const userId = localStorage.getItem('userId');
    if (!userId) return;

    // Check if user has shop profile
    getShopProfile(userId)
      .then(shop => {
        setShopProfile(shop);
        if (shop) {
          // Try to get existing score
          return getReadinessScore(userId)
            .then(s => {
              if (s) {
                setScore(s);
              } else {
                // Calculate score if doesn't exist
                return calculateReadinessScore(userId).then(s => setScore(s));
              }
            })
            .catch(() => {
              // Calculate score if get fails
              return calculateReadinessScore(userId).then(s => setScore(s));
            });
        }
      })
      .catch(() => {
        setError('Could not load your profile');
      })
      .finally(() => {
        setLoading(false);
      });
  }, []);

  const fullName = localStorage.getItem('fullName') || '';

  if (loading) {
    return (
      <section className="space-y-4">
        <p className="text-sm text-slate-600">Loading...</p>
      </section>
    );
  }

  return (
    <section className="space-y-6">
      <h1 className="text-2xl font-semibold">Welcome {fullName || 'to your dashboard'}</h1>

      {!shopProfile && (
        <div className="bg-blue-50 border border-blue-200 rounded-lg p-4">
          <h2 className="font-medium text-blue-900 mb-2">Complete Your Profile</h2>
          <p className="text-sm text-blue-700 mb-3">
            To get matched with franchises and see your readiness score, please complete your shop profile.
          </p>
          <Link
            to="/shop-profile"
            className="inline-flex items-center rounded-md bg-blue-600 px-4 py-2 text-white text-sm"
          >
            Complete Shop Profile
          </Link>
        </div>
      )}

      {shopProfile && score && (
        <div className="bg-white border border-slate-200 rounded-lg p-6 space-y-4">
          <h2 className="text-xl font-semibold">Your Readiness Score</h2>
          <div className="flex items-center gap-4">
            <div className="text-4xl font-bold text-blue-600">
              {Math.round(score.totalScore || 0)}
            </div>
            <div className="flex-1">
              <div className="w-full bg-slate-200 rounded-full h-4">
                <div
                  className="bg-blue-600 h-4 rounded-full transition-all"
                  style={{ width: `${score.totalScore || 0}%` }}
                />
              </div>
              <p className="text-xs text-slate-600 mt-1">Out of 100</p>
            </div>
          </div>

          {score.recommendations && (
            <div className="bg-slate-50 rounded-md p-4">
              <h3 className="text-sm font-medium mb-2">How to Improve Your Score</h3>
              <p className="text-sm text-slate-700">{score.recommendations}</p>
            </div>
          )}

          <div className="pt-4 border-t border-slate-200">
            <Link
              to="/shop-profile"
              className="text-sm text-blue-600 hover:underline"
            >
              Update your profile →
            </Link>
          </div>
        </div>
      )}

      {shopProfile && !score && (
        <div className="bg-yellow-50 border border-yellow-200 rounded-lg p-4">
          <p className="text-sm text-yellow-800">
            Your profile is complete. We're calculating your readiness score...
          </p>
        </div>
      )}

      <div className="bg-slate-50 rounded-lg p-4">
        <h2 className="font-medium mb-2">What's Next?</h2>
        <ul className="text-sm text-slate-700 space-y-1 list-disc list-inside">
          <li>Matched franchises will appear here based on your profile and readiness score</li>
          <li>You can browse available franchises and apply</li>
          <li>Subscription plans will unlock more features and leads</li>
        </ul>
      </div>

      {error && <p className="text-sm text-red-600">{error}</p>}
    </section>
  );
}

