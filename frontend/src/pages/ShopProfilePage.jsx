import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { saveShopProfile, getShopProfile } from '../services/apiClient';

const BUSINESS_CATEGORIES = [
  { value: 'ELECTRONICS', label: 'Electronics' },
  { value: 'AUTOMOBILE', label: 'Automobile' },
  { value: 'FOOD_BEVERAGE', label: 'Food & Beverage' },
  { value: 'RETAIL', label: 'Retail' },
  { value: 'DAIRY', label: 'Dairy' },
  { value: 'PHARMACY', label: 'Pharmacy' },
  { value: 'BEAUTY_SALON', label: 'Beauty Salon' },
  { value: 'FITNESS', label: 'Fitness' },
  { value: 'EDUCATION', label: 'Education' },
  { value: 'OTHER', label: 'Other' }
];

const SERVICE_TYPES = [
  { value: 'REPAIR', label: 'Repair' },
  { value: 'MAINTENANCE', label: 'Maintenance' },
  { value: 'INSTALLATION', label: 'Installation' },
  { value: 'CONSULTATION', label: 'Consultation' },
  { value: 'SALES', label: 'Sales' },
  { value: 'RENTAL', label: 'Rental' },
  { value: 'WARRANTY_SERVICE', label: 'Warranty Service' },
  { value: 'CUSTOMIZATION', label: 'Customization' },
  { value: 'TRAINING', label: 'Training' },
  { value: 'DELIVERY', label: 'Delivery' },
  { value: 'OTHER', label: 'Other' }
];

const LOCALITY_TYPES = [
  { value: 'COMMERCIAL', label: 'Commercial' },
  { value: 'RESIDENTIAL', label: 'Residential' },
  { value: 'INDUSTRIAL', label: 'Industrial' },
  { value: 'MIXED', label: 'Mixed' },
  { value: 'HIGH_STREET', label: 'High Street' },
  { value: 'MALL', label: 'Mall' },
  { value: 'MARKET_AREA', label: 'Market Area' }
];

const STEPS = [
  { id: 1, title: 'Business Identity', description: 'Tell us about your shop' },
  { id: 2, title: 'Services & Capability', description: 'What you offer' },
  { id: 3, title: 'Franchise Interest', description: 'What franchises interest you' },
  { id: 4, title: 'Location & Infrastructure', description: 'Where you are and what you have' },
  { id: 5, title: 'Verification', description: 'Build trust with photos and documents' }
];

export function ShopProfilePage() {
  const navigate = useNavigate();
  const [currentStep, setCurrentStep] = useState(1);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [formData, setFormData] = useState({
    // Business Identity
    shopName: '',
    category: '',
    yearsInOperation: '',
    numberOfEmployees: '',
    monthlyCustomers: '',
    shopAreaSqFt: '',
    numberOfOutlets: '',
    // Services & Capability
    servicesOffered: [],
    certificationUrls: [],
    brandsServiced: [],
    // Franchise Interest
    interestedFranchiseDomains: [],
    willingToRelocate: false,
    // Location & Infrastructure
    address: '',
    locality: '',
    localityType: '',
    latitude: 0,
    longitude: 0,
    parkingAvailable: false,
    powerBackup: false,
    coldStorage: false,
    // Verification
    photoUrls: [],
    licenseUrl: '',
    gstUrl: '',
    allowCustomerRatings: true
  });

  useEffect(() => {
    const userId = localStorage.getItem('userId');
    if (!userId) {
      navigate('/signup');
      return;
    }
    // Try to load existing profile
    getShopProfile(userId).then(data => {
      if (data) {
        setFormData({
          shopName: data.shopName || '',
          category: data.category || '',
          yearsInOperation: data.yearsInOperation || '',
          numberOfEmployees: data.numberOfEmployees || '',
          monthlyCustomers: data.monthlyCustomers || '',
          shopAreaSqFt: data.shopAreaSqFt || '',
          numberOfOutlets: data.numberOfOutlets || '',
          servicesOffered: data.servicesOffered || [],
          certificationUrls: data.certificationUrls || [],
          brandsServiced: data.brandsServiced || [],
          interestedFranchiseDomains: data.interestedFranchiseDomains || [],
          willingToRelocate: data.willingToRelocate || false,
          address: data.address || '',
          locality: data.locality || '',
          localityType: data.localityType || '',
          latitude: data.latitude || 0,
          longitude: data.longitude || 0,
          parkingAvailable: data.parkingAvailable || false,
          powerBackup: data.powerBackup || false,
          coldStorage: data.coldStorage || false,
          photoUrls: data.photoUrls || [],
          licenseUrl: data.licenseUrl || '',
          gstUrl: data.gstUrl || '',
          allowCustomerRatings: data.allowCustomerRatings !== undefined ? data.allowCustomerRatings : true
        });
      }
    }).catch(() => {
      // No existing profile, start fresh
    });
  }, [navigate]);

  const handleChange = (field, value) => {
    setFormData(prev => ({ ...prev, [field]: value }));
  };

  const handleMultiSelect = (field, value) => {
    setFormData(prev => {
      const current = prev[field] || [];
      const updated = current.includes(value)
        ? current.filter(item => item !== value)
        : [...current, value];
      return { ...prev, [field]: updated };
    });
  };

  const handleNext = () => {
    if (currentStep < STEPS.length) {
      setCurrentStep(currentStep + 1);
    }
  };

  const handleBack = () => {
    if (currentStep > 1) {
      setCurrentStep(currentStep - 1);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const userId = localStorage.getItem('userId');
    if (!userId) {
      setError('Please signup first');
      return;
    }

    setLoading(true);
    setError('');

    try {
      // Auto-save everything
      await saveShopProfile(userId, {
        ...formData,
        yearsInOperation: parseInt(formData.yearsInOperation) || 0,
        numberOfEmployees: parseInt(formData.numberOfEmployees) || 0,
        monthlyCustomers: parseInt(formData.monthlyCustomers) || 0,
        shopAreaSqFt: parseFloat(formData.shopAreaSqFt) || 0,
        numberOfOutlets: parseInt(formData.numberOfOutlets) || 1
      });
      
      navigate('/dashboard');
    } catch (err) {
      setError('Could not save your profile. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  const renderStep1 = () => (
    <div className="space-y-4">
      <h2 className="text-xl font-semibold">Business Identity</h2>
      <p className="text-sm text-slate-600">
        This helps us understand your business scale and match you with the right franchises.
      </p>
      
      <div>
        <label className="block text-sm font-medium mb-1">Shop Name *</label>
        <input
          type="text"
          value={formData.shopName}
          onChange={(e) => handleChange('shopName', e.target.value)}
          className="w-full rounded-md border border-slate-300 px-3 py-2"
          required
        />
      </div>

      <div>
        <label className="block text-sm font-medium mb-1">Business Category *</label>
        <select
          value={formData.category}
          onChange={(e) => handleChange('category', e.target.value)}
          className="w-full rounded-md border border-slate-300 px-3 py-2"
          required
        >
          <option value="">Select category</option>
          {BUSINESS_CATEGORIES.map(cat => (
            <option key={cat.value} value={cat.value}>{cat.label}</option>
          ))}
        </select>
      </div>

      <div className="grid grid-cols-2 gap-4">
        <div>
          <label className="block text-sm font-medium mb-1">Years in Operation *</label>
          <input
            type="number"
            value={formData.yearsInOperation}
            onChange={(e) => handleChange('yearsInOperation', e.target.value)}
            className="w-full rounded-md border border-slate-300 px-3 py-2"
            min="0"
            required
          />
        </div>
        <div>
          <label className="block text-sm font-medium mb-1">Number of Employees *</label>
          <input
            type="number"
            value={formData.numberOfEmployees}
            onChange={(e) => handleChange('numberOfEmployees', e.target.value)}
            className="w-full rounded-md border border-slate-300 px-3 py-2"
            min="0"
            required
          />
        </div>
      </div>

      <div className="grid grid-cols-2 gap-4">
        <div>
          <label className="block text-sm font-medium mb-1">Monthly Customers *</label>
          <input
            type="number"
            value={formData.monthlyCustomers}
            onChange={(e) => handleChange('monthlyCustomers', e.target.value)}
            className="w-full rounded-md border border-slate-300 px-3 py-2"
            min="0"
            required
          />
        </div>
        <div>
          <label className="block text-sm font-medium mb-1">Shop Area (sq ft) *</label>
          <input
            type="number"
            value={formData.shopAreaSqFt}
            onChange={(e) => handleChange('shopAreaSqFt', e.target.value)}
            className="w-full rounded-md border border-slate-300 px-3 py-2"
            min="0"
            step="0.01"
            required
          />
        </div>
      </div>

      <div>
        <label className="block text-sm font-medium mb-1">Number of Outlets *</label>
        <input
          type="number"
          value={formData.numberOfOutlets}
          onChange={(e) => handleChange('numberOfOutlets', e.target.value)}
          className="w-full rounded-md border border-slate-300 px-3 py-2"
          min="1"
          required
        />
      </div>
    </div>
  );

  const renderStep2 = () => (
    <div className="space-y-4">
      <h2 className="text-xl font-semibold">Services & Capability</h2>
      <p className="text-sm text-slate-600">
        This helps franchises understand what you can offer.
      </p>

      <div>
        <label className="block text-sm font-medium mb-2">Services Offered</label>
        <div className="grid grid-cols-2 gap-2">
          {SERVICE_TYPES.map(service => (
            <label key={service.value} className="flex items-center gap-2">
              <input
                type="checkbox"
                checked={formData.servicesOffered.includes(service.value)}
                onChange={() => handleMultiSelect('servicesOffered', service.value)}
                className="rounded border-slate-300"
              />
              <span className="text-sm">{service.label}</span>
            </label>
          ))}
        </div>
      </div>

      <div>
        <label className="block text-sm font-medium mb-1">Certification URLs (optional)</label>
        <input
          type="text"
          placeholder="Enter URLs separated by commas"
          value={formData.certificationUrls.join(', ')}
          onChange={(e) => handleChange('certificationUrls', e.target.value.split(',').map(s => s.trim()).filter(s => s))}
          className="w-full rounded-md border border-slate-300 px-3 py-2"
        />
        <p className="text-xs text-slate-500 mt-1">You can upload files later</p>
      </div>

      <div>
        <label className="block text-sm font-medium mb-1">Brands Serviced (optional)</label>
        <input
          type="text"
          placeholder="Enter brand names separated by commas"
          value={formData.brandsServiced.join(', ')}
          onChange={(e) => handleChange('brandsServiced', e.target.value.split(',').map(s => s.trim()).filter(s => s))}
          className="w-full rounded-md border border-slate-300 px-3 py-2"
        />
      </div>
    </div>
  );

  const renderStep3 = () => (
    <div className="space-y-4">
      <h2 className="text-xl font-semibold">Franchise Interest</h2>
      <p className="text-sm text-slate-600">
        Which franchise domains interest you? This helps us show relevant matches.
      </p>

      <div>
        <label className="block text-sm font-medium mb-2">Interested Franchise Domains</label>
        <div className="grid grid-cols-2 gap-2">
          {BUSINESS_CATEGORIES.map(cat => (
            <label key={cat.value} className="flex items-center gap-2">
              <input
                type="checkbox"
                checked={formData.interestedFranchiseDomains.includes(cat.value)}
                onChange={() => handleMultiSelect('interestedFranchiseDomains', cat.value)}
                className="rounded border-slate-300"
              />
              <span className="text-sm">{cat.label}</span>
            </label>
          ))}
        </div>
      </div>

      <div>
        <label className="flex items-center gap-2">
          <input
            type="checkbox"
            checked={formData.willingToRelocate}
            onChange={(e) => handleChange('willingToRelocate', e.target.checked)}
            className="rounded border-slate-300"
          />
          <span className="text-sm">Willing to relocate for the right franchise opportunity</span>
        </label>
      </div>
    </div>
  );

  const renderStep4 = () => (
    <div className="space-y-4">
      <h2 className="text-xl font-semibold">Location & Infrastructure</h2>
      <p className="text-sm text-slate-600">
        Location details help franchises find you, and infrastructure shows your readiness.
      </p>

      <div>
        <label className="block text-sm font-medium mb-1">Address *</label>
        <input
          type="text"
          value={formData.address}
          onChange={(e) => handleChange('address', e.target.value)}
          className="w-full rounded-md border border-slate-300 px-3 py-2"
          placeholder="Full address"
          required
        />
      </div>

      <div className="grid grid-cols-2 gap-4">
        <div>
          <label className="block text-sm font-medium mb-1">Locality *</label>
          <input
            type="text"
            value={formData.locality}
            onChange={(e) => handleChange('locality', e.target.value)}
            className="w-full rounded-md border border-slate-300 px-3 py-2"
            required
          />
        </div>
        <div>
          <label className="block text-sm font-medium mb-1">Locality Type *</label>
          <select
            value={formData.localityType}
            onChange={(e) => handleChange('localityType', e.target.value)}
            className="w-full rounded-md border border-slate-300 px-3 py-2"
            required
          >
            <option value="">Select type</option>
            {LOCALITY_TYPES.map(type => (
              <option key={type.value} value={type.value}>{type.label}</option>
            ))}
          </select>
        </div>
      </div>

      <div className="grid grid-cols-2 gap-4">
        <div>
          <label className="block text-sm font-medium mb-1">Latitude *</label>
          <input
            type="number"
            value={formData.latitude}
            onChange={(e) => handleChange('latitude', parseFloat(e.target.value) || 0)}
            className="w-full rounded-md border border-slate-300 px-3 py-2"
            step="0.000001"
            required
          />
        </div>
        <div>
          <label className="block text-sm font-medium mb-1">Longitude *</label>
          <input
            type="number"
            value={formData.longitude}
            onChange={(e) => handleChange('longitude', parseFloat(e.target.value) || 0)}
            className="w-full rounded-md border border-slate-300 px-3 py-2"
            step="0.000001"
            required
          />
        </div>
      </div>
      <p className="text-xs text-slate-500">You can use Google Maps to get coordinates</p>

      <div className="space-y-2">
        <label className="block text-sm font-medium mb-2">Infrastructure</label>
        <label className="flex items-center gap-2">
          <input
            type="checkbox"
            checked={formData.parkingAvailable}
            onChange={(e) => handleChange('parkingAvailable', e.target.checked)}
            className="rounded border-slate-300"
          />
          <span className="text-sm">Parking available</span>
        </label>
        <label className="flex items-center gap-2">
          <input
            type="checkbox"
            checked={formData.powerBackup}
            onChange={(e) => handleChange('powerBackup', e.target.checked)}
            className="rounded border-slate-300"
          />
          <span className="text-sm">Power backup</span>
        </label>
        <label className="flex items-center gap-2">
          <input
            type="checkbox"
            checked={formData.coldStorage}
            onChange={(e) => handleChange('coldStorage', e.target.checked)}
            className="rounded border-slate-300"
          />
          <span className="text-sm">Cold storage (for dairy businesses)</span>
        </label>
      </div>
    </div>
  );

  const renderStep5 = () => (
    <div className="space-y-4">
      <h2 className="text-xl font-semibold">Verification & Trust</h2>
      <p className="text-sm text-slate-600">
        Photos and documents help franchises trust your business. Minimum 3 photos recommended.
      </p>

      <div>
        <label className="block text-sm font-medium mb-1">Shop Photos (URLs, minimum 3 recommended)</label>
        <input
          type="text"
          placeholder="Enter photo URLs separated by commas"
          value={formData.photoUrls.join(', ')}
          onChange={(e) => handleChange('photoUrls', e.target.value.split(',').map(s => s.trim()).filter(s => s))}
          className="w-full rounded-md border border-slate-300 px-3 py-2"
        />
        <p className="text-xs text-slate-500 mt-1">You can upload files later</p>
      </div>

      <div>
        <label className="block text-sm font-medium mb-1">License URL (optional)</label>
        <input
          type="text"
          value={formData.licenseUrl}
          onChange={(e) => handleChange('licenseUrl', e.target.value)}
          className="w-full rounded-md border border-slate-300 px-3 py-2"
          placeholder="License document URL"
        />
      </div>

      <div>
        <label className="block text-sm font-medium mb-1">GST URL (optional)</label>
        <input
          type="text"
          value={formData.gstUrl}
          onChange={(e) => handleChange('gstUrl', e.target.value)}
          className="w-full rounded-md border border-slate-300 px-3 py-2"
          placeholder="GST document URL"
        />
      </div>

      <div>
        <label className="flex items-center gap-2">
          <input
            type="checkbox"
            checked={formData.allowCustomerRatings}
            onChange={(e) => handleChange('allowCustomerRatings', e.target.checked)}
            className="rounded border-slate-300"
          />
          <span className="text-sm">Allow customer ratings on my profile</span>
        </label>
      </div>
    </div>
  );

  const renderCurrentStep = () => {
    switch (currentStep) {
      case 1: return renderStep1();
      case 2: return renderStep2();
      case 3: return renderStep3();
      case 4: return renderStep4();
      case 5: return renderStep5();
      default: return null;
    }
  };

  return (
    <section className="max-w-3xl mx-auto space-y-6">
      <div>
        <h1 className="text-2xl font-semibold">Complete Your Shop Profile</h1>
        <p className="text-sm text-slate-600 mt-1">
          Step {currentStep} of {STEPS.length}: {STEPS[currentStep - 1].title}
        </p>
      </div>

      <div className="flex gap-2 mb-6">
        {STEPS.map((step) => (
          <div
            key={step.id}
            className={`flex-1 h-2 rounded ${
              step.id <= currentStep ? 'bg-blue-600' : 'bg-slate-200'
            }`}
            title={step.title}
          />
        ))}
      </div>

      <form onSubmit={currentStep === STEPS.length ? handleSubmit : (e) => { e.preventDefault(); handleNext(); }} className="space-y-6">
        {renderCurrentStep()}

        {error && <p className="text-sm text-red-600">{error}</p>}

        <div className="flex gap-4">
          {currentStep > 1 && (
            <button
              type="button"
              onClick={handleBack}
              className="px-4 py-2 border border-slate-300 rounded-md text-sm"
            >
              Back
            </button>
          )}
          <button
            type="submit"
            disabled={loading}
            className="ml-auto inline-flex items-center rounded-md bg-blue-600 px-4 py-2 text-white text-sm disabled:opacity-70"
          >
            {loading ? 'Saving...' : currentStep === STEPS.length ? 'Save & Continue' : 'Next'}
          </button>
        </div>
      </form>
    </section>
  );
}
